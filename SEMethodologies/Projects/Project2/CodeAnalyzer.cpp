/*
 	CodeAnalyzer.cpp
 
 	Do codeanalyzery things here...
	 
	Rob Bauer
	rbauer@tekro.com
*/

#include "CodeAnalyzer.hpp"
#include "ParseReport.hpp"
#include <iostream>

// analyze and report based on the search path and search terms given
void CodeAnalyzer::analyze(std::string* path, std::string* terms) {
	std::cout << "Analyzing... " << *path << "\n";
	ParseReport *report = new ParseReport();
	std::vector<ParsedResult *> *results;

	// TODO: loop files in path
	queries.push_back(new Query(*path));

	// loop the queries
	for(int a = 0; a < queries.size(); a++) {
		results = queries[a]->query(terms);
		
		// for all of the results found for this query, add to the report
		for(int b = 0; b < results->size(); b++) {
			report->addResult((*results)[b]);
		}
	}

	report->generate(*path, "ReportFilename");
}