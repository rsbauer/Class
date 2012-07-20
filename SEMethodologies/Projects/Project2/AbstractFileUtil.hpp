/*
	AbstractFileUtil.hpp

	Abstract file utility functions.  

	Rob Bauer
	rbauer@tekro.com
*/

#ifndef INCLUDED_ABSTRACTFILEUTIL_HPP
#define INCLUDED_ABSTRACTFILEUTIL_HPP

#include <vector>
#include <iostream>
#include "ParsedResult.hpp"

class AbstractFileUtil {
protected:
	std::string path;

public:
	void setPath(const std::string& path);
	virtual void open();
	virtual std::string* read();
	virtual void write(const std::string* data);
	virtual void close();

	virtual std::vector<ParsedResult *> *query(const std::string* terms);
	virtual std::string* generateReport(std::vector<const ParsedResult *> &results);
};

#endif