/*
  This program will take all of the input files and cat them to individual output files.

  Note: command line parsing is primitive and will accept any delimiter between the
  source and destination file besides "-o" as specified in the requirements.  

  Rob Bauer
  rbauer@tekro.com
*/

#include <stdio.h>
#include <stdlib.h>
#include "copy.h"

int main(int argc, char *argv[]) {

	int a = 0;
	int index = 0;
	int srcdestCount = argc / 3;
	char *source;
	char *destination;
	CopySourceDestination srcdestArray[srcdestCount];

	for (a = 1; a < argc; a += 3) {
		source = argv[a];
		destination = NULL;
		index = a / 3;

		// check if destination was specified
		if (a + 2 < argc)
			destination = argv[a + 2];

		if (source != NULL && destination != NULL) {
			// have valid source/destination combo so add to the struct array
			srcdestArray[index].source = source;
			srcdestArray[index].destination = destination;
		}
	}

	// perform the copy
	copy(srcdestCount, srcdestArray);

	return 0;
}