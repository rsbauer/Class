/*
 	wordlist.cpp
 
 	Implement Concordance functions
	 
	Robert S. Bauer
	rbauer@tekro.com
*/

#include <string>
#include <vector>
#include <iostream>

// split words by the following characters
const std::string SPLIT_ON_CHARS = " .,;:!?";

//	Given text as a string and a string of splitter characters, returns a vector 
//	of strings in the text
std::vector<std::string> split(const std::string &sentence, const std::string &splitOn)
{
	char *ptrToken;
	char *sentence2split = strdup(sentence.c_str());
	std::vector<std::string> tokens;
	
	// loop through the tokens storing them and checking if out of tokens
	while((ptrToken = strtok(sentence2split, splitOn.c_str())) != NULL)
	{
		tokens.push_back(ptrToken);
		
		// need to reset sentence2split so the next token can be retrieved
		sentence2split = NULL;
	}
	
	free(sentence2split);
	
	return tokens;
}


// given a string, return a vector of unique, sorted words
std::vector<std::string> words(const std::string &sentence)
{
	std::vector<std::string>::iterator it;
	
	std::vector<std::string> words = split(sentence, SPLIT_ON_CHARS);

	std::sort(words.begin(), words.end());

	// use default comparison
	it = std::unique(words.begin(), words.end());
	words.resize(it - words.begin());

	return words;
}
