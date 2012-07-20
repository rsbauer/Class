/*
 	Client.cpp
 
 	Do client-y things here...
	 
	Rob Bauer
	rbauer@tekro.com
*/

#include <iostream>
#include "CodeAnalyzer.hpp"

int main()
{
	// setup the analyzer
	CodeAnalyzer *analyzer = new CodeAnalyzer();

	// analyze and report 
	analyzer->analyze(new std::string("directory path to analyze"), new std::string("terms to search"));
	
	return 0;
}
