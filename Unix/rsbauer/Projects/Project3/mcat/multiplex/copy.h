/*
  Copy a file using multiplexing

  Rob Bauer
  rbauer@tekro.com
*/

#ifndef INCLUDED_COPY_H
#define INCLUDED_COPY_H

#include <fcntl.h>

#define BLOCK_SIZE 512

// keep track of source/destination relationship
typedef struct {
	int sourceFileDescriptor;
	int destinationFileDescriptor;
	char *source;
	char *destination;
	char buffer[BLOCK_SIZE];
	ssize_t bytesread;
	ssize_t byteswritten;
} CopySourceDestination;

// multiplex copy source to destination files
int copy(const int srcdestCount, CopySourceDestination *srcdestArray);
int readfile(CopySourceDestination *srcdestArray);
int writefile(CopySourceDestination *srcdestArray);
int openfile(const char *file, const int flags, const int mode);
void closefile(const int filedescriptor, const char *file);

#endif