##
# BigInt Make

## Executable ##
all : BigInt_s

BigInt_s : BigInt_s.o vadd.o BigInt.o
	g++ BigInt_s.o vadd.o BigInt.o -o BigInt_s

BigInt_s.o : BigInt.cpp BigInt.hpp BigInt_s.cpp
	g++ -c BigInt_s.cpp 

vadd.o : vadd.cpp vadd.hpp
	g++ -c vadd.cpp

BigInt.o : BigInt.cpp BigInt.hpp
	g++ -c BigInt.cpp

## Tests ##
BigInt_t.o : BigInt_t.cpp BigInt.hpp BigInt.cpp
	g++ -c BigInt_t.cpp

BigInt_t : BigInt_t.o BigInt.o
	g++ BigInt_t.o BigInt.o vadd.o -o BigInt_t

test : BigInt_t
	./BigInt_t


## House Cleaning ##
clean :
	rm -f BigInt_s BigInt_s.o BigInt_t BigInt_t.o BigInt.o vadd.o
	
