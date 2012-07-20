/*
	FileUtil_C.cpp

	Implement AbstractFileUtil methods for C files

	Rob Bauer
	rbauer@tekro.com
*/

#include "FileUtil_C.hpp"

// read the contents of the file
std::string* FileUtil_C::read() {
	std::cout << "C read(" << path << ")\n";
	return new std::string("data from read()");
}

// write string data to file
void FileUtil_C::write(const std::string& data) {
	std::cout << "C write(" << path << ") with data: " << data << "\n";
}

// query the file and return an array of results
std::vector<ParsedResult *> *FileUtil_C::query(const std::string* terms) {
	open();
	// TODO: read while(!EOF)
	read();
	std::cout << "C query()\n";
	std::vector<ParsedResult *> *results = new std::vector<ParsedResult *>();
	close();
	return results;
}

// generate a report based on the results given
std::string* FileUtil_C::generateReport(std::vector<const ParsedResult *> &results) {
	return new std::string("C report data");
}

