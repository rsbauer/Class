#include "XmlTxtWriter.hpp"
#include <fstream>

XmlTxtWriter::XmlTxtWriter(const std::string& filename)
	: out (filename.c_str(), std::ios_base::out)
{
	if(!out.is_open())
	{
		throw sprintf("Could not open file %s", filename.c_str());
	}

	out << "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n";
	out << "<Model>\n";
}


XmlTxtWriter::~XmlTxtWriter(void)
{
	close();
}

// close the stream
void XmlTxtWriter::close()
{
	// check if the stream is still open and close it
	if(out.is_open())
	{
		out << "</Model>\n";

		out.close();
	}
}

// write an int
void XmlTxtWriter::writeInt(const std::string& name, int value)
{
	out << "\t<" << name.c_str() << ">" << value << "</" << name.c_str() << ">\n";
}

// write a string
void XmlTxtWriter::writeStr(const std::string& name, const std::string& value)
{
	out << "\t<" << name.c_str() << ">" << value.c_str() << "</" << name.c_str() << ">\n";
}

