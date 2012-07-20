/*
	FileUtil_CSharp.hpp
	
	Rob Bauer
	rbauer@tekro.com
*/

#ifndef INCLUDED_FILEUTILCSHARP_HPP
#define INCLUDED_FILEUTILCSHARP_HPP

#include "AbstractFileUtil.hpp"

class FileUtil_CSharp : public AbstractFileUtil {

public:
	std::string* read();
	void write(const std::string& data);

	std::vector<ParsedResult *> *query(const std::string* terms);
	std::string* generateReport(std::vector<const ParsedResult *> &results);
};

#endif