#pragma once
#ifndef INCLUDED_MODEL_HPP
#define INCLUDED_MODEL_HPP

#include "Writer.hpp"
#include "Reader.hpp"
#include "Serializable.hpp"
#include <iostream>

class Model : public Serializable
{
public:
	// constructors and destructors
	Model(void);
	~Model(void);

	// setters 
	void setValue(int);
	void setValue(std::string);

	// getters
	int getInteger();
	std::string getStr();

	// for serialization
	void writeTo(Writer*) const;
	void readFrom(Reader*);

private:
	// some simple data types for testing
	int integer;
	std::string str;
};

#endif
