/********************************************************************************
** Form generated from reading UI file 'echowindow.ui'
**
** Created: Sun Nov 14 17:53:34 2010
**      by: Qt User Interface Compiler version 4.7.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_ECHOWINDOW_H
#define UI_ECHOWINDOW_H

#include <QtCore/QVariant>
#include <QtGui/QAction>
#include <QtGui/QApplication>
#include <QtGui/QButtonGroup>
#include <QtGui/QHeaderView>
#include <QtGui/QMainWindow>
#include <QtGui/QMenuBar>
#include <QtGui/QStatusBar>
#include <QtGui/QToolBar>
#include <QtGui/QWidget>

QT_BEGIN_NAMESPACE

class Ui_EchoWindowClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *EchoWindowClass)
    {
        if (EchoWindowClass->objectName().isEmpty())
            EchoWindowClass->setObjectName(QString::fromUtf8("EchoWindowClass"));
        EchoWindowClass->resize(600, 400);
        menuBar = new QMenuBar(EchoWindowClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        EchoWindowClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(EchoWindowClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        EchoWindowClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(EchoWindowClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        EchoWindowClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(EchoWindowClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        EchoWindowClass->setStatusBar(statusBar);

        retranslateUi(EchoWindowClass);

        QMetaObject::connectSlotsByName(EchoWindowClass);
    } // setupUi

    void retranslateUi(QMainWindow *EchoWindowClass)
    {
        EchoWindowClass->setWindowTitle(QApplication::translate("EchoWindowClass", "EchoWindow", 0, QApplication::UnicodeUTF8));
    } // retranslateUi

};

namespace Ui {
    class EchoWindowClass: public Ui_EchoWindowClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_ECHOWINDOW_H
