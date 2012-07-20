/*
 	FileUtil.cpp
 
 	Perform basic file utilities.  Implements AbstractFileUtil.
	 
	Rob Bauer
	rbauer@tekro.com
*/

#include "FileUtil.hpp"
#include <iostream>

// open file
void FileUtil::open() {
	std::cout << "FileUtil open(" << path << ")\n";
}

// read from a file and return its contents
std::string* FileUtil::read() {
	std::cout << "FileUtil read(" << path << ")\n";
	return new std::string("data from read()");
}

// write data string to the file
void FileUtil::write(const std::string* data) {
	std::cout << "FileUtil write(" << path << ") with data: " << *data << "\n";
}

// close the file
void FileUtil::close() {
	std::cout << "FileUtil close(" << path << ")\n";
}

// query the file and return a list of results
std::vector<ParsedResult *> *FileUtil::query(const std::string* terms) {
	open();
	// TODO: read while(!EOF)
	read();
	std::cout << "FileUtil query()\n";
	std::vector<ParsedResult *> *results = new std::vector<ParsedResult *>();
	close();
	return results;
}

// generate a report based on the results 
std::string* FileUtil::generateReport(std::vector<const ParsedResult *> &results) {
	return new std::string("FileUtil report data");
}
