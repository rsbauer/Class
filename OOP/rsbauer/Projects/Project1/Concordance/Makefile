##
# Project 1: Concordance 
#
# Robert S Bauer
# rbauer@tekro.com
##

all : Concordance

Concordance : Concordance.o wordlist.o
	g++ Concordance.o wordlist.o -o Concordance

Concordance.o : Concordance.cpp 
	g++ -c Concordance.cpp

wordlist.o : wordlist.cpp wordlist.hpp
	g++ -c wordlist.cpp

split_t : split_t.o wordlist.o
	g++ split_t.o wordlist.o -o split_t

split_t.o : wordlist.cpp wordlist.hpp split_t.cpp
	g++ -c split_t.cpp

words_t : words_t.o wordlist.o
	g++ words_t.o wordlist.o -o words_t

words_t.o : wordlist.cpp wordlist.hpp words_t.cpp
	g++ -c words_t.cpp
	
test : split_t words_t
	./split_t
	./words_t
	
testsplit : split_t
	./split_t

testwords : words_t
	./words_t	
	
clean :
	rm -f Concordance.o wordlist.o Concordance split_t.o split_t words_t.o words_t

