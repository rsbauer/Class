#pragma once
#ifndef INCLUDED_XMLTXTREADER_HPP
#define INCLUDED_XMLTXTREADER_HPP

#include "Reader.hpp"
#include "XmlStructureParser.hpp"
#include <fstream>
#include <QFile>
#include <QXmlInputSource>
#include <QXmlSimpleReader>
#include <QDebug>


class XmlTxtReader
	: public Reader
{
public:
	XmlTxtReader(const std::string& filename);
	~XmlTxtReader(void);

	// read objects
	std::string readString(const std::string& name);
	int readInt(const std::string& name);


private:
	std::string lookupValue(const std::string& name);
	std::map<QString, QString> xmldata;
};

#endif

