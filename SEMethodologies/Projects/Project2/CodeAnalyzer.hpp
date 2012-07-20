/*
	CodeAnalyzer.hpp

	CodeAnalyzer functions.  

	Rob Bauer
	rbauer@tekro.com
*/

#ifndef INCLUDED_CODEANALYZER_HPP
#define INCLUDED_CODEANALYZER_HPP

#include <string>
#include <vector>
#include "Query.hpp"

class CodeAnalyzer {
private:
	std::vector<Query *> queries;

public:
	void analyze(std::string* path, std::string* terms);
};

#endif
