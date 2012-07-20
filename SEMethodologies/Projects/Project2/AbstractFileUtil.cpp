/*
 	AbstractFileUtil.cpp
 
 	Abstract class for FileUtil.  Implements basic file commands.
	 
	Rob Bauer
	rbauer@tekro.com
*/

#include "AbstractFileUtil.hpp"
#include <iostream>

// set the path for the file being worked on
void AbstractFileUtil::setPath(const std::string& filepath) {
	path = filepath;
}

// open the file
void AbstractFileUtil::open() {
	std::cout << "AbstractFileUtil open(" << path << ")\n";
}

// read from the file and return the contents
std::string* AbstractFileUtil::read() {
	std::cout << "AbstractFileUtil read(" << path << ")\n";
	return new std::string("data from read()");
}

// write the given data to the file
void AbstractFileUtil::write(const std::string* data) {
	std::cout << "AbstractFileUtil write(" << path << ") with data: " << *data << "\n";
}

// close the file
void AbstractFileUtil::close() {
	std::cout << "AbstractFileUtil close(" << path << ")\n";
}

// query the file using the terms given, returning an array of results
std::vector<ParsedResult *> *AbstractFileUtil::query(const std::string* terms) {
	open();
	// TODO: read while(!EOF)
	read();
	std::cout << "AbstractFileUtil query()\n";
	std::vector<ParsedResult *> *results = new std::vector<ParsedResult *>();
	close();
	return results;
}

// generate a report based on the results given
std::string* AbstractFileUtil::generateReport(std::vector<const ParsedResult *> &results) {
	return new std::string("Report data");
}
