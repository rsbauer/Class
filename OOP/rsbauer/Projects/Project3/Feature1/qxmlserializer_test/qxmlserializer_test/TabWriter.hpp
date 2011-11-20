#pragma once
#ifndef INCLUDED_TABWRITER_HPP
#define INCLUDED_TABWRITER_HPP

#include "writer.hpp"
#include <fstream>

// Write output in tab delimited format
class TabWriter :
	public Writer
{
public:
	// constructor taking a file name as its parameter
	TabWriter(const std::string& filename);
	~TabWriter(void);

	// write objects
	void writeInt(const std::string& name, int value);
	void writeStr(const std::string& name, const std::string& value);

	// close the stream
	void close();

private:
	// the delimiter used when a new object is writen 
	void addDelimiter();
	
	// the output stream
	std::ofstream out;
};

#endif