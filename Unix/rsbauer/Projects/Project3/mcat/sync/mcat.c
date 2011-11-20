/*
  This program will take all of the input files and cat them to individual output files.

  Rob Bauer
  rbauer@tekro.com
*/

#include <stdio.h>
#include "copy.h"

int main(int argc, char* argv[]) {

	int a = 0;
	char *source;
	char *destination;

	for (a = 1; a < argc; a += 3) {
		source = argv[a];
		destination = NULL;

		if (a + 2 < argc)
			destination = argv[a + 2];

		if (source != NULL && destination != NULL) {

			if(copy(source, destination) != 0)
				printf("Failed to copy %s to %s", source, destination);
		}
	}

	return 0;
}