/*
	FileUtil_CPlusPlus.hpp

	Rob Bauer
	rbauer@tekro.com
*/

#ifndef INCLUDED_FILEUTILCPLUSPLUS_HPP
#define INCLUDED_FILEUTILCPLUSPLUS_HPP

#include "AbstractFileUtil.hpp"

class FileUtil_CPlusPlus : public AbstractFileUtil {

public:
	std::string* read();
	void write(const std::string& data);

	std::vector<ParsedResult *> *query(const std::string* terms);
	std::string* generateReport(std::vector<const ParsedResult *> &results);
};

#endif