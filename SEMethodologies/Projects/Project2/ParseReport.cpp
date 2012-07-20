/*
 	ParseReport.cpp
 
 	Generate a ParseReport
	 
	Robert S. Bauer
	rbauer@tekro.com
*/

#include "ParseReport.hpp"
#include "AbstractFileUtil.hpp"
#include "FileUtil.hpp"
#include "FileFactory.hpp"
#include <iostream>

void ParseReport::generate(const std::string& searchpath, const std::string& reportFilename) {
	std::cout << "Generate ParseReport for: " << reportFilename << "\n";

	FileFactory* fileFactory = new FileFactory();
	AbstractFileUtil* file = fileFactory->createFileUtil(searchpath);
	file->setPath(searchpath);
	
	AbstractFileUtil* reportFile = new FileUtil();
	reportFile->setPath(reportFilename);

	reportFile->open();
	reportFile->write(file->generateReport(results));
	reportFile->close();
}

void ParseReport::addResult(const ParsedResult* result) {
	results.push_back(result);
	std::cout << "Added result: " << result->match << "\n";
}