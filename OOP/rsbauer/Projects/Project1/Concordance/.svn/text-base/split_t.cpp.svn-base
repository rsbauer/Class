/*
 	split_t.cpp
 
 	Split method unit tests
	 
	Robert S. Bauer
	rbauer@tekro.com
*/

#include <cassert>
#include <iostream>
#include <vector>
#include <string>
#include "wordlist.hpp"

void blankString_resultEmptyVector_test();
void simpleSplit_resultVectorWithWords_test();
void multipleDelimiter_resultVectorWithWords_test();

int main()
{
	blankString_resultEmptyVector_test();
	simpleSplit_resultVectorWithWords_test();
	multipleDelimiter_resultVectorWithWords_test();
	return 0;
}

// Test passing in blank string for both the string to split and the 
// string defining the characters to split on
void blankString_resultEmptyVector_test()
{
	std::cout << "blankString_resultEmptyVector_test... ";
	std::string testString = "";
	std::vector<std::string> tokens = split(testString, testString);
	
	assert(tokens.size() == 0); 
	assert(tokens.empty()); 

	std::cout << "Passed\n";
}

// Perform some simple string splits using one character to split on
void simpleSplit_resultVectorWithWords_test()
{
	std::cout << "simpleSplit_resultVectorWithWords_test... ";
	std::string testString = "This is a test";
	std::string splitOn = " ";
	std::vector<std::string> tokens = split(testString, splitOn);
	
	// check the size and contents
	assert(tokens.size() == 4); 
	assert(!tokens.empty()); 
	assert(tokens[0] == "This");
	assert(tokens[3] == "test");
	
	std::cout << "Passed\n";
}

// Test if splitting with multiple delimiters work
void multipleDelimiter_resultVectorWithWords_test()
{
	std::cout << "multipleDelimiter_resultVectorWithWords_test... ";
	std::string testString = "This,is.a:test";
	std::string splitOn = ":.,";
	std::vector<std::string> tokens = split(testString, splitOn);
	
	// check the size and contents
	assert(tokens.size() == 4); 
	assert(!tokens.empty()); 
	assert(tokens[0] == "This");
	assert(tokens[3] == "test");
	
	std::cout << "Passed\n";
}
