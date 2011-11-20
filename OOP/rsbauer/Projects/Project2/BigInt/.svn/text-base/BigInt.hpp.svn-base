/*
	BigInt.hpp

	BigInt functions.  Perform infinite precision arithmetic.

	Rob Bauer
	rbauer@tekro.com
*/

#ifndef INCLUDED_BIGINT_HPP
#define INCLUDED_BIGINT_HPP

#include "vadd.hpp"
#include <iostream>
#include <vector>

class BigInt
{
private:
	std::vector<int> digits;

public:
	// Default constructor
	BigInt();

	// Copy constructor
	BigInt(const BigInt &);
	
	// Initialize BigInt with an specified integer
	BigInt(const int);

	// Initialize BigInt with a string
	BigInt(const std::string &);

	// Initialize BigInt with char *
	BigInt(const char *);

	// get the number of digits stored
	int size();

	// convert a string to digits
	void convertString(const char *);

	// Stream output
	friend std::ostream& operator<<(std::ostream &, const BigInt &);

	// add numbers using +=
	const BigInt& operator+=(const BigInt &);

	// add numbers using +
	friend BigInt operator+(const BigInt &, const BigInt &);

	// test BigInt to a string
	bool operator==(const std::string &);

	// convert BigInt to string
	std::string toString();

};

// Stream output
std::ostream& operator<<(std::ostream &, const BigInt &);

// adding
BigInt operator+(const BigInt &, const BigInt &);

#endif

