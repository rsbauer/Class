##
# Build mcat
##

## Executable ##
all : mcat

mcat : mcat.o copy.o
	gcc -std=c99 mcat.o copy.o -o mcat	

mcat.o : mcat.c
	gcc -c -std=c99 mcat.c -o mcat.o

copy.o : copy.c
	gcc -c -std=c99 copy.c -o copy.o

## House Cleaning ##
clean :
	rm -f mcat mcat.o copy.o
