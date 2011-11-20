/*
 	split_t.cpp
 
 	Word list method unit tests
	 
	Robert S. Bauer
	rbauer@tekro.com
*/

#include <cassert>
#include <iostream>
#include <vector>
#include <string>
#include "wordlist.hpp"

void blankString_resultEmptyVector_test();
void someDuplicateWords_resultUniqueWordList_test();
void sortUniqueWords_resultSortedWordList_test();
void passed();

int main()
{
	blankString_resultEmptyVector_test();
	sortUniqueWords_resultSortedWordList_test();
	someDuplicateWords_resultUniqueWordList_test();
	return 0;
}

// Test passing in blank string for both the string to split and the 
// string defining the characters to split on
void blankString_resultEmptyVector_test()
{
	std::cout << "blankString_resultEmptyVector_test... ";
	std::vector<std::string> tokens = words("");
	
	// check the size
	assert(tokens.size() == 0); 
	assert(tokens.empty()); 

	passed();
}

// try sorting a word list that already contains unique words 
void sortUniqueWords_resultSortedWordList_test()
{
	std::cout << "sortUniqueWords_resultSortedWordList_test... ";

	std::vector<std::string> tokens = words("cc bb aa");
	
	// check the size and contents
	assert(tokens.size() == 3);
	assert(tokens[0] == "aa");
	assert(tokens[2] == "cc");
	
	passed();
}

// test everything - sorting and listing only unique words
void someDuplicateWords_resultUniqueWordList_test()
{
	std::cout << "someDuplicateWords_resultUniqueWordList_test... ";

	std::vector<std::string> tokens = words("aa cc bb cc bb aa");
	
	// check the size and contents
	assert(tokens.size() == 3);
	assert(tokens[0] == "aa");
	assert(tokens[2] == "cc");
	
	passed();
}

// let the user know the test passed
void passed()
{
	std::cout << "Passed\n";
}
