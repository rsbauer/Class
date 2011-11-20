##
# Build mcat
##

## Executable ##
all : mcat

mcat : mcat.o copy.o
	gcc mcat.o copy.o -o mcat	

mcat.o : mcat.c
	gcc -c mcat.c -o mcat.o

copy.o : copy.c
	gcc -c copy.c -o copy.o

## House Cleaning ##
clean :
	rm -f mcat mcat.o copy.o
