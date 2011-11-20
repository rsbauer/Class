/*
  BigInt_t.cpp

  BigInt unit tests

  Rob Bauer
  rbauer@tekro.com
*/

#include "BigInt.hpp"
#include <cassert>
#include <iostream>
#include <sstream>

void testOutput();
void testAdding();
void testComparison();
void testStringStream(const BigInt &, const char *);

int main() 
{
	BigInt b;
	BigInt b2(b);
	BigInt b3(123);
	BigInt b4("123");

	// a silly test to verify the addresses are not the same
	assert(&b2 != &b);


	b = b2;
	assert(&b != &b2);
	assert(b == "");

	// not able to test much other than verify the proper constructor is firing
	b = "123";

	// test the size
	assert(b3.size() == 3);		// init with int
	assert(b3 == "123");

	assert(b.size() == 3);		// init with str
	assert(b == "123");

	// test copy constructor
	BigInt b5(b4);
	assert(b5.size() == 3);
	assert(b5 == "123");

	BigInt b6(b3);
	assert(b6.size() == 3);
	assert(b6 == "123");

	// test really big numbers
	BigInt big("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
	assert(big.size() == 250);

	// test bad input
	BigInt bad("asdf");
	assert(bad.size() == 0);

	testOutput();

	testAdding();

	testComparison();

	return 0;
}


// Test output, both cout << and to a file
void testOutput()
{
	BigInt b;

	// test empty BigInt
	testStringStream(b, "");

	// test string
	b = "123";
	testStringStream(b, "123");

	// test int
	b = 456;
	testStringStream(b, "456");

}

// test string stream
void testStringStream(const BigInt &bint, const char *expected)
{
	std::ostringstream sout;
	sout << bint;
	assert(sout.str() == expected);
}


// test adding BigInts together
void testAdding()
{
	BigInt add1(9999);
	BigInt add2(1);
	BigInt add3 = add1 + add2;

	assert(add3.size() == 5);
	assert(add3.toString() == "10000");
	assert(add3 == "10000");

	add1 += add2;
	assert(add1.size() == 5);
	assert(add1.toString() == "10000");
	assert(add1 == "10000");
	assert(add2.size() == 1);
	assert(add2 == "1");
}

// test comparison operators
// right now, only == supported
void testComparison()
{
	BigInt a(123);

	assert(a == "123");
	assert(a.toString() == "123");
}

