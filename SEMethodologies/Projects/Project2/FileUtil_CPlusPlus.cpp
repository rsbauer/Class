/*
	FileUtil_CPlusPlus.cpp

	Implement AbstractFileUtil for C++ files.

	Rob Bauer
	rbauer@tekro.com
*/

#include "FileUtil_CPlusPlus.hpp"

// read and return file contents
std::string* FileUtil_CPlusPlus::read() {
	std::cout << "C++ read(" << path << ")\n";
	return new std::string("data from read()");
}

// write data to file
void FileUtil_CPlusPlus::write(const std::string& data) {
	std::cout << "C++ write(" << path << ") with data: " << data << "\n";
}

// query the file and return an array of results
std::vector<ParsedResult *> *FileUtil_CPlusPlus::query(const std::string* terms) {
	open();
	// TODO: read while(!EOF)
	read();
	std::cout << "C++ query()\n";
	std::vector<ParsedResult *> *results = new std::vector<ParsedResult *>();
	close();
	return results;
}

// generate a report based on the given results
std::string* FileUtil_CPlusPlus::generateReport(std::vector<const ParsedResult *> &results) {
	return new std::string("C++ report data");
}

