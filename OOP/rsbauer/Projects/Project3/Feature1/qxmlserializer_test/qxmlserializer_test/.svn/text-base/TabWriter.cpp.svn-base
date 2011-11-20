#include "TabWriter.hpp"
#include <fstream>


// constructor with a filename given - try to open the file or throw an error
TabWriter::TabWriter(const std::string& filename)
	: out (filename.c_str(), std::ios_base::out)
{
	if(!out.is_open())
	{
		throw sprintf("Could not open file %s", filename.c_str());
	}
}

// destructor
TabWriter::~TabWriter(void)
{
	close();
}

// write an int
void TabWriter::writeInt(const std::string& name, int value)
{
	addDelimiter();
	out << value;
}

// write a string
void TabWriter::writeStr(const std::string& name, const std::string& value)
{
	addDelimiter();
	out << value.c_str();
}

// close the stream
void TabWriter::close()
{
	// check if the stream is still open and close it
	if(out.is_open())
	{
		out.close();
	}
}

// check if a delimiter is necessary and add it to the stream
void TabWriter::addDelimiter()
{
	if(out.tellp() > 0)
	{
		out << "\t";
	}
}
