/*
	FileUtil_Java.cpp

	Implement AbstractFileUtil class for Java files.

 	Rob Bauer
	rbauer@tekro.com
*/

#include "FileUtil_Java.hpp"

// read and return file contents
std::string* FileUtil_Java::read() {
	std::cout << "Java read(" << path << ")\n";
	return new std::string("data from read()");
}

// write data to file
void FileUtil_Java::write(const std::string& data) {
	std::cout << "Java write(" << path << ") with data: " << data << "\n";
}

// query the file and return an array of results
std::vector<ParsedResult *> *FileUtil_Java::query(const std::string* terms) {
	open();
	// TODO: read while(!EOF)
	read();
	std::cout << "Java query()\n";
	std::vector<ParsedResult *> *results = new std::vector<ParsedResult *>();
	close();
	return results;
}

// generate a report based on the given results
std::string* FileUtil_Java::generateReport(std::vector<const ParsedResult *> &results) {
	return new std::string("Java report data");
}

