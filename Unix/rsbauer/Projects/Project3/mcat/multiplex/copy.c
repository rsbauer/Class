/*
  Copy a file using multiplexing

  Code based on mcopy.c from examples/mio/mcopy.c
  (https://dev.cs.uakron.edu/trac/cs428F11/browser/001/examples/mio/mcopy.c)

  Rob Bauer
  rbauer@tekro.com
*/

#include "copy.h"

#include <stdio.h>
#include <errno.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/select.h>

// copy the source file to the destination
int copy(const int srcdestCount, CopySourceDestination *srcdestArray) {
	
	char buf[BLOCK_SIZE];
	fd_set readset;
	fd_set writeset;
	int maxfd = -9999;
	char *inputfile;
	char *outputfile;

	FD_ZERO(&readset);
	FD_ZERO(&writeset);

	// setup the file descriptors and config fd_set 
	for (int b = 0; b < srcdestCount; b++) {
		
		// open the input file
		if((srcdestArray[b].sourceFileDescriptor = openfile(srcdestArray[b].source, O_RDONLY, 0)) < 0)
			return 1;

		// open the output file
		if((srcdestArray[b].destinationFileDescriptor = openfile(srcdestArray[b].destination, O_WRONLY | O_CREAT, S_IRUSR | S_IWUSR)) < 0)
			return 1;

		// add the files to the file descriptor set
		FD_SET(srcdestArray[b].sourceFileDescriptor, &readset);
		FD_SET(srcdestArray[b].destinationFileDescriptor, &writeset);

		// determine the max file descriptor by comparing the current max to the input/output descriptor
		maxfd = (srcdestArray[b].sourceFileDescriptor > maxfd ? srcdestArray[b].sourceFileDescriptor : maxfd);
		maxfd = (srcdestArray[b].destinationFileDescriptor > maxfd ? srcdestArray[b].destinationFileDescriptor : maxfd);
	}

	// perform some reading/writing
	for(int numfiles = srcdestCount * 2; numfiles > 0;) {
			
		fd_set curreadset = readset;
		fd_set curwriteset = writeset;
	
		// block until a file is available for read or write
		int num = select(maxfd + 1, &curreadset, &curwriteset, NULL, NULL);
		if (num == -1)
			break;

		// loop the descriptors
		for (int b = 0; b < srcdestCount; b++) {
			
			// look for files to read from
			if (FD_ISSET(srcdestArray[b].sourceFileDescriptor, &curreadset)) {
				if(readfile(&srcdestArray[b]) != 0)
					break;

				if(srcdestArray[b].bytesread == 0) {
					// done with this file
					FD_CLR(srcdestArray[b].sourceFileDescriptor, &readset);
					numfiles--;
				}				
      		}

      		// look for files to write to
			if (FD_ISSET(srcdestArray[b].destinationFileDescriptor, &curwriteset)) {
				if(writefile(&srcdestArray[b]) != 0)
					break;

				if(srcdestArray[b].bytesread == 0) {
					// done with this file
					FD_CLR(srcdestArray[b].destinationFileDescriptor, &writeset);
					numfiles--;
				}				
      		}
		}
	}

	// close the files
	for (int b = 0; b < srcdestCount; b++) {

		closefile(srcdestArray[b].sourceFileDescriptor, srcdestArray[b].source);
		closefile(srcdestArray[b].destinationFileDescriptor, srcdestArray[b].destination);
	}

	return 0;	
}

// read from the file and store the data in the buffer
int readfile(CopySourceDestination *srcdestArray) {

	// read data
	while (((srcdestArray->bytesread = read(srcdestArray->sourceFileDescriptor, srcdestArray->buffer, BLOCK_SIZE)) == -1) && (errno == EINTR));

	// handle read error
	if (srcdestArray->bytesread == -1) {
		perror(srcdestArray->source);
		return 1;
	}

	return 0;
}

// write data from the buffer to the file
int writefile(CopySourceDestination *srcdestArray) {

	// write data
	char* bufwrite = srcdestArray->buffer;
	while (srcdestArray->bytesread > 0) {

		// write what you can
		while (((srcdestArray->byteswritten = write(srcdestArray->destinationFileDescriptor, bufwrite, srcdestArray->bytesread)) == -1) && (errno == EINTR));

		// error
		if (srcdestArray->byteswritten < 0) {
			perror(srcdestArray->destination);
			return 1;
		}

		// position in output buffer
		srcdestArray->bytesread -= srcdestArray->byteswritten;
		bufwrite += srcdestArray->byteswritten;
	}			

	return 0;
}

int openfile(const char *file, const int flags, const int mode) {
	int filedescriptor = 0;
	while (((filedescriptor = open(file, flags, mode)) == -1) &&
		(errno == EINTR));

	if (filedescriptor < 0) {
	  perror(file);
	  return -1;
	}

	return filedescriptor;
}

// close the given file
void closefile(const int filedescriptor, const char *file) {
	int result = 0;
	while (((result = close(filedescriptor)) == -1) && (errno == EINTR));

	if (result == -1)
		perror(file);
}
