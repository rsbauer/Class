#pragma once
#ifndef INCLUDED_MODEL_H
#define INCLUDED_MODEL_H

#include <iostream>

class Model
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

private:
        // some simple data types for testing
        int integer;
        std::string str;
};

#endif
