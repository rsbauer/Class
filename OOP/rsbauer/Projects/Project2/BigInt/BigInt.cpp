/*
	BigInt.cpp

	BigInt function definitions.  Perform infinite precision arithmetic.

	Rob Bauer
	rbauer@tekro.com
*/

#include "BigInt.hpp"
#include "vadd.hpp"
#include <iostream>
#include <sstream>
#include <vector>


// Default constructor
BigInt::BigInt()
{
}


// Copy constructor
BigInt::BigInt(const BigInt &bint)
{
	digits = bint.digits;
}

// Initialize BigInt with an integer
BigInt::BigInt(const int number)
{
	int value = number;
	while(value > 0)
	{
		digits.push_back(value % 10);
		value = value / 10;
	}
}

// Initialize BigInt with a string
BigInt::BigInt(const std::string &str)
{
	convertString(str.c_str());
}

// Initialize BigInt with a char *
BigInt::BigInt(const char *str)
{
	convertString(str);
}

// return the size of digits
int BigInt::size()
{
	return digits.size();
}

// convert a string to digits
void BigInt::convertString(const char *str)
{
	int num = 0;

	while(*str != '\0')
	{
		// get one character at a time
		num = *str++;

		// make sure the char is in numeric range
		if(num >= '0' && num <= '9')
		{
			// convert to int
			num -= 48;

			// add to the digits list
			digits.insert(digits.begin(), 1, num);
		}
	}
}


// Stream output
std::ostream& operator<<(std::ostream &out, const BigInt &bint)
{
	for(int a = bint.digits.size() - 1; a > -1; a--)
	{
		out << bint.digits[a];
	}

	return out;
}


// add numbers using += 
const BigInt& BigInt::operator+=(const BigInt &right)
{
	add(this->digits, right.digits);
	return *this;	
}


// add numbers using + syntax
BigInt operator+(const BigInt &left, const BigInt &right)
{
	BigInt target(left);
	target += right;


	return target;	
}

// test BigInt against a string 
bool BigInt::operator==(const std::string &right)
{
	return this->toString() == right;
}

// convert a BigInt to a string
std::string BigInt::toString()
{
	std::ostringstream sout;
	sout << *this;
	return sout.str();
}


