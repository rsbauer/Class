/*
	ParseReport.hpp

	ParseReport functions.  

	Rob Bauer
	rbauer@tekro.com
*/

#ifndef INCLUDED_ParseReport_HPP
#define INCLUDED_ParseReport_HPP

#include "ParsedResult.hpp"
#include <vector>

class ParseReport {
private:
	std::vector<const ParsedResult*> results;

public:
	void generate(const std::string& searchpath, const std::string& reportFilename);
	void addResult(const ParsedResult* result);
};

#endif
