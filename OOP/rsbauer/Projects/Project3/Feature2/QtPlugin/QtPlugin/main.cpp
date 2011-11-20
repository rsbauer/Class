
#include <QtCore/QCoreApplication>
#include <QtPlugin>

/*
Sample code to work out the details on using Qt's plugin system

Code and info from http://doc.qt.nokia.com/4.7/plugins-howto.html
*/

Q_IMPORT_PLUGIN(BasicSave)

int main(int argc, char *argv[])
{
	QCoreApplication a(argc, argv);

	return 0;
}



