#include "XmlStructureParser.hpp"
#include <QXmlDefaultHandler>
#include <map>

// Code from "An Introduction to Design Patterns in C++ with QT 4" pages 326-329

QTextStream cout(stdout, QIODevice::WriteOnly);

bool XmlStructureParser::startDocument()
{
	return TRUE;
}

bool XmlStructureParser::characters(const QString& text) 
{
	elementData = text;
	return TRUE;
}

bool XmlStructureParser::startElement(const QString& namespaceURI, 
	const QString& localName, const QString& qName, const QXmlAttributes& atts)
{
	return TRUE;
}

bool XmlStructureParser::endElement(const QString& namespaceURI, 
	const QString& localName, const QString& qName)
{
	elementData = elementData.trimmed();
	if(elementData.length() > 0)
	{
		// cout << qName << " = " << elementData << "\n";
		xmldata[qName] = elementData;
	}
	
	return TRUE;
}


std::map<QString, QString> XmlStructureParser::getXmlData()
{
	return xmldata;
}