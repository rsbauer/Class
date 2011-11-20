
#include "TabWriter.hpp"
#include "TabReader.hpp"
#include "XmlTxtWriter.hpp"
#include "XmlTxtReader.hpp"
#include <QtCore\QCoreApplication>
#include <QDebug>
#include <QXmlSerializer>
#include <QXmlQuery>
#include <QBuffer>
#include <Model.hpp>
#include <cassert>

/*
Serializable design pattern from:
http://dirkriehle.com/computer-science/research/1996/plop-1996-serializer.pdf

No Qt libraries used to serialize

Qt QXmlSimpleReader was used to read/parse XML

This app was built using Visual Studio.  
.PRO and .PRI files were exported using Qt's Vs addin. 
Please contact Rob (rbauer@tekro.com) if there are compile problems
*/
int main(int argc, char *argv[])
{
	QCoreApplication a(argc, argv);
	
	Model model;
	model.setValue(1);
	model.setValue("Test");

	TabWriter writer ("testfile.txt");
	model.writeTo(&writer);
	writer.close();
	
	std::cout << "Created test file\n";

	Model two;
	TabReader reader ("testfile.txt");
	two.readFrom(&reader);
	reader.close();
	
	std::cout << two.getStr() << " " << two.getInteger() << "\n";

	// unit test
	assert(model.getStr() == two.getStr());
	assert(model.getInteger() == two.getInteger());

	// output xml
	two.setValue(2);
	two.setValue("Two");
	XmlTxtWriter xmlout ("testfile.xml");
	two.writeTo(&xmlout);
	xmlout.close();

	XmlTxtReader xmlreader ("testfile.xml");
	model.readFrom(&xmlreader);

	std::cout << "After XML read:\n";
	std::cout << model.getStr() << " " << model.getInteger() << "\n";
	
	assert(model.getStr() == "Two");
	assert(model.getInteger() == 2);

	return 0;
}
