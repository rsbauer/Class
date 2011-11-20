#pragma once
#ifndef INCLUDED_INTERFACES_HPP
#define INCLUDED_INTERFACES_HPP

#include <QtPlugin>

class SaveInterface
{
public:
    virtual ~SaveInterface() {}

    virtual void save(const std::string& filename) const = 0;


};


QT_BEGIN_NAMESPACE

Q_DECLARE_INTERFACE(BasicSave, "com.rsbauer.QtPlugin.SaveInterface/1.0")

QT_END_NAMESPACE

#endif

