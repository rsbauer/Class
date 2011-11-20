#ifndef ECHOINTERFACE_H
#define ECHOINTERFACE_H

#include <QString>

class EchoInterface
{
public:
    virtual ~EchoInterface() {}
    virtual QString echo(const QString &message) = 0;
};

Q_DECLARE_INTERFACE(EchoInterface,
                    "com.trolltech.Plugin.EchoInterface/1.0");

#endif
