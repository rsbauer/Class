#pragma once
#ifndef INCLUDED_XMLTXTWRITER_HPP
#define INCLUDED_XMLTXTWRITER_HPP

#include "Writer.hpp"
#include <fstream>

class XmlTxtWriter
	: public Writer
{
public:
	XmlTxtWriter(const std::string& filename);
	~XmlTxtWriter(void);

	// write objects
	void writeInt(const std::string& name, int value);
	void writeStr(const std::string& name, const std::string& value);

	// close the stream
	void close();

private:
	// the output stream
	std::ofstream out;	
};

#endif
