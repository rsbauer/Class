/*
	FileUtil_DepedencyGraph.cpp

	Implement AbstractFileUtil class for dependency graphs.

 	Rob Bauer
	rbauer@tekro.com
*/

#include "FileUtil_DependencyGraph.hpp"

// read and return file contents
std::string* FileUtil_DependencyGraph::read() {
	std::cout << "Dependency graph read(" << path << ")\n";
	return new std::string("data from read()");
}

// write data to file
void FileUtil_DependencyGraph::write(const std::string& data) {
	std::cout << "Dependency graph write(" << path << ") with data: " << data << "\n";
}

// query the file and return an array of results
std::vector<ParsedResult *> *FileUtil_DependencyGraph::query(const std::string* terms) {
	open();
	// TODO: read while(!EOF)
	read();
	std::cout << "Dependency graph query()\n";
	std::vector<ParsedResult *> *results = new std::vector<ParsedResult *>();
	close();
	return results;
}

// generate a report based on the given results
std::string* FileUtil_DependencyGraph::generateReport(std::vector<const ParsedResult *> &results) {
	return new std::string("Dependency graph report data");
}

