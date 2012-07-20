/*
	FileUtil_Java.hpp
	
	Rob Bauer
	rbauer@tekro.com
*/

#ifndef INCLUDED_FILEUTILJAVA_HPP
#define INCLUDED_FILEUTILJAVA_HPP

#include "AbstractFileUtil.hpp"

class FileUtil_Java : public AbstractFileUtil {

public:
	std::string* read();
	void write(const std::string& data);

	std::vector<ParsedResult *> *query(const std::string* terms);
	std::string* generateReport(std::vector<const ParsedResult *> &results);
};

#endif