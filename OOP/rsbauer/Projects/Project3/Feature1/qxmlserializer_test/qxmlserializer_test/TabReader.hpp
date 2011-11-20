#pragma once
#ifndef INCLUDED_TABREADER_HPP
#define INCLUDED_TABREADER_HPP

#include "Reader.hpp"
#include <iostream>
#include <fstream>
#include <string>

class TabReader
	: public Reader
{
public:
	TabReader(const std::string& filename);
	~TabReader(void);

	// read objects
	std::string readString(const std::string& name);
	int readInt(const std::string& name);

	// close the stream
	void close();

private:
		std::ifstream in;
		std::string line;
};

#endif
