/*
 	Query.cpp
 
 	Query the specified file. The file can be of any type supported by 
 	the FileFactory.
	 
	Rob Bauer
	rbauer@tekro.com
*/

#include "Query.hpp"
#include "ParsedResult.hpp"
#include "FileFactory.hpp"
#include <iostream>

// Query constructor which takes a path of the file to query
// Setup the AbstractFileUtil type by using the FileFactory 
Query::Query(const std::string& path) {
	FileFactory* fileFactory = new FileFactory();
	file = fileFactory->createFileUtil(path);
	file->setPath(path);
}

// query the file and return an array of results
std::vector<ParsedResult *> *Query::query(const std::string* terms) {
	std::cout << "Query() for terms: " << *terms << "\n";

	return file->query(terms);
}