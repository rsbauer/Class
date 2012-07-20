/*
	Query.hpp

	Query functions.  

	Rob Bauer
	rbauer@tekro.com
*/

#ifndef INCLUDED_QUERY_HPP
#define INCLUDED_QUERY_HPP

#include "AbstractFileUtil.hpp"
#include "ParsedResult.hpp"
#include <vector>
	
class Query {
private:
	AbstractFileUtil* file;

public:
	Query(const std::string& path);
	std::vector<ParsedResult *> *query(const std::string* terms);

};

#endif
