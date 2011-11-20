/*
 	Concordance.cpp
 
 	The purpose of this program is to text (through standard input) and create 
	a list of sorted, unique, words in the text. You will solve this problem by 
	implementing the following functions:

    * split - given text as a string and a string of splitter characters, 
		returns a vector of strings in the text
    * words - given a vector of words, returns a vector of unique, sorted words

	 
	Robert S. Bauer
	rbauer@tekro.com
*/

#include <iostream>
#include <string>
#include <vector>
#include "wordlist.hpp"

// display a token
void displayToken(const std::string &);

int main()
{
	int input;
	std::string sentence = "";
	std::cout << "Please enter a sentence for concordance calculation\n: ";

	// grab some input
	while((input = std::cin.get()) != '\n')
	{
		sentence.push_back(input);
	}
	
	// calculate concordance
	std::vector<std::string> tokens = words(sentence);

	// display the results
	for_each(tokens.begin(), tokens.end(), displayToken);
	
	return 0;
}

// Display a token
void displayToken(const std::string &token)
{
	std::cout << token << "\n";
}
