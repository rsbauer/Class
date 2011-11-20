#pragma once
#ifndef INCLUDED_SERIALIZABLE_HPP
#define INCLUDED_SERIALIZABLE_HPP

#include "Writer.hpp"
#include "Reader.hpp"

/*
Serializable design pattern from:
http://dirkriehle.com/computer-science/research/1996/plop-1996-serializer.pdf
*/

// Serializable interface
class Serializable
{
public:
	virtual void readFrom(Reader*)  =0;
	virtual void writeTo(Writer*) const =0;
};

#endif

