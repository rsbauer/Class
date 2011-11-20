/*
  Copy a file NOT using multiplexing

  Code based on copy.c from examples/io/copy.c
  (https://dev.cs.uakron.edu/trac/cs428F11/browser/001/examples/io/copy.c)

  Rob Bauer
  rbauer@tekro.com
*/

#include "copy.h"

#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <errno.h>
#include <unistd.h>
#include <fcntl.h>

#define BLOCK_SIZE 512


// copy the source file to the destination
int copy(const char *source, const char *destination) {
	  char buf[BLOCK_SIZE];

	
	// make sure a file name was provided for source and destination
	if (strlen(source) == 0 || strlen(destination) == 0)
		return 1;
	
	// // //   OPEN FILES   // // //

	// open the source file
	int inputFileDescriptor = -1;
    while (((inputFileDescriptor = open(source, O_RDONLY)) == -1) &&
        (errno == EINTR))
 		;

	if (inputFileDescriptor < 0) {
		perror(source);
		return 1;
	}

	// open the output file
	int outputFileDescriptor = -1;
    while (((outputFileDescriptor = open(destination, O_WRONLY | O_CREAT, S_IRUSR | S_IWUSR)) == -1) && (errno == EINTR))
		;

	if (outputFileDescriptor < 0) {
		perror(destination);
		return 1;
	}

	// // //   COPY   // // //

	while(1) {

		// read data from standard input
		ssize_t bytesread;

		while (((bytesread = read(inputFileDescriptor, buf, BLOCK_SIZE)) == -1) && (errno == EINTR));

		// handle eof
		if (bytesread == 0)
			break;

		// handle read error
		if (bytesread == -1) {
			perror(source);
			break;
		}

		// write the data to standard output
		char* bufwrite = buf;
		while (bytesread > 0) {

		// write what you can
		ssize_t byteswritten;
		while (((byteswritten = write(outputFileDescriptor, bufwrite, bytesread)) == -1) && (errno == EINTR));

		// error
		if (byteswritten < 0)
			break;

		// position in output buffer
		bytesread -= byteswritten;
		bufwrite += byteswritten;
		}
	}


	// // //   CLOSE FILES   // // //

	// close the input file
	int result = 0;
	if (inputFileDescriptor != STDIN_FILENO) {
		while (((result = close(inputFileDescriptor)) == -1) &&
		     (errno == EINTR))
		;
	}

	if (result == -1)
		perror(source);

	// close the output file
	result = 0;
	if (outputFileDescriptor != STDOUT_FILENO) {
		while (((result = close(outputFileDescriptor)) == -1) &&
		     (errno == EINTR))
		;
	}

	if (result == -1)
		perror(destination);

	return 0;	
}
