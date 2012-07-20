/*
	FileUtil_CallGraph.cpp

	Implement AbstractFileUtil class for call graph files.

	Rob Bauer
	rbauer@tekro.com
*/

#include "FileUtil_CallGraph.hpp"

// read and return file contents
std::string* FileUtil_CallGraph::read() {
	std::cout << "Call graph read(" << path << ")\n";
	return new std::string("data from read()");
}

// write data to file
void FileUtil_CallGraph::write(const std::string& data) {
	std::cout << "Call graph write(" << path << ") with data: " << data << "\n";
}

// query the file and return an array of results
std::vector<ParsedResult *> *FileUtil_CallGraph::query(const std::string* terms) {
	open();
	// TODO: read while(!EOF)
	read();
	std::cout << "Call graph query()\n";
	std::vector<ParsedResult *> *results = new std::vector<ParsedResult *>();
	close();
	return results;
}

// generate a report based on the given results
std::string* FileUtil_CallGraph::generateReport(std::vector<const ParsedResult *> &results) {
	return new std::string("Call graph report data");
}

