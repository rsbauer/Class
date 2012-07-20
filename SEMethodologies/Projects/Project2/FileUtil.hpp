/*
	FileUtil.hpp

	File utility functions.  

	Rob Bauer
	rbauer@tekro.com
*/

#ifndef INCLUDED_FILEUTIL_HPP
#define INCLUDED_FILEUTIL_HPP

#include "AbstractFileUtil.hpp"
#include <string>

class FileUtil : public AbstractFileUtil {

public:
	void open();
	std::string* read();
	void write(const std::string* data);
	void close();
	std::vector<ParsedResult *> *query(const std::string* terms);
	std::string* generateReport(std::vector<const ParsedResult *> &results);
};

#endif
