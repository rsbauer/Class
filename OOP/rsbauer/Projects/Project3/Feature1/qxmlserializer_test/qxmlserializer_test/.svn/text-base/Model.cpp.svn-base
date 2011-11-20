#include "Model.hpp"
#include "Writer.hpp"
#include "Reader.hpp"
#include <iostream>

// default constructor
Model::Model(void)
{
}


// destrutor
Model::~Model(void)
{
}

// set the integer value
void Model::setValue(int value)
{
	integer = value;
}

// set the string value
void Model::setValue(std::string value)
{
	str = value;
}

// get the integer
int Model::getInteger()
{
	return integer;
}

// get the string
std::string Model::getStr()
{
	return str;
}

// for serialization
void Model::writeTo(Writer* writer) const
{
	writer->writeStr("str", str);
	writer->writeInt("integer", integer);
}

void Model::readFrom(Reader* reader)
{
	str = reader->readString("str");
	integer = reader->readInt("integer");
}

