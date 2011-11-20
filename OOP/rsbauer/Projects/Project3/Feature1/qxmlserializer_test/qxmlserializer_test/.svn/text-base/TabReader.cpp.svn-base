#include "TabReader.hpp"
#include <iostream>
#include <fstream>
#include <string>

TabReader::TabReader(const std::string& filename)
{
	in.open(filename.c_str(), std::ifstream::in);
	 
	if(!in)
	{
		std::cerr << "Failed to open file " << filename.c_str();
		return;
	}

	if(!in.is_open())
	{
		std::cerr << "Failed to open file " << filename.c_str();
		return;
	}

	std::getline(in, line);
}


TabReader::~TabReader(void)
{
	close();
}

// read a string
std::string TabReader::readString(const std::string& name)
{
	char* token = strtok(strdup(line.c_str()), "\t");

	return token;
}

// read an integer
int TabReader::readInt(const std::string& name)
{
	// get first item
	strtok(strdup(line.c_str()), "\t");

	// grab 2nd item which is what we want
	char* token = strtok(NULL, "\t");

	// convert char* to int
	return std::atoi(token);
}

// close the stream
void TabReader::close()
{
	if(in.is_open())
	{
		in.close();
	}
}

