#pragma once
#ifndef INCLUDED_XMLSTRUCTUREPARSER_HPP
#define INCLUDED_XMLSTRUCTUREPARSER_HPP

#include <QXmlDefaultHandler>
#include <map>

class XmlStructureParser
	: public QXmlDefaultHandler
{
public:
	bool startDocument();
	bool startElement(const QString& namespaceURI, 
		const QString& localName,
		const QString& qName,
		const QXmlAttributes& atts);

	bool characters(const QString& text);
	bool endElement(const QString& namespaceURI,
		const QString& localName,
		const QString& qName);

	std::map<QString, QString> getXmlData();

private:
	QString elementData;
	std::map<QString, QString> xmldata;
};


#endif
