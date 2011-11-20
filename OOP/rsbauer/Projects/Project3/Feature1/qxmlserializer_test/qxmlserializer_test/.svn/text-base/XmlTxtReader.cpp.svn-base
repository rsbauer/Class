#include "XmlTxtReader.hpp"
#include "XmlStructureParser.hpp"
#include <iostream>
#include <fstream>
#include <string>
#include <QFile>
#include <QXmlInputSource>
#include <QXmlSimpleReader>
#include <QDebug>

XmlTxtReader::XmlTxtReader(const std::string& filename)
{
	QFile xmlFile(filename.c_str());
	QXmlInputSource source(&xmlFile);
	XmlStructureParser handler;
	QXmlSimpleReader reader;
	reader.setContentHandler(&handler);
	reader.parse(source);
	xmldata = handler.getXmlData();
}


XmlTxtReader::~XmlTxtReader(void)
{
}


// read a string
std::string XmlTxtReader::readString(const std::string& name)
{
	return lookupValue(name);
}

// read an integer
int XmlTxtReader::readInt(const std::string& name)
{
	return std::atoi(lookupValue(name).c_str());
}

std::string XmlTxtReader::lookupValue(const std::string& name)
{
	QString qname = QString(name.c_str());
	std::map<QString, QString>::iterator it = xmldata.find(qname);

	if(it == xmldata.end())
	{
		return "";
	}

	return it->second.toStdString();
}
