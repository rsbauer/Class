/*
	FileFactory.hpp

	File factory - load the proper file class based on the file name.

	Rob Bauer
	rbauer@tekro.com
*/

#ifndef INCLUDED_FILEFACTORY_HPP
#define INCLUDED_FILEFACTORY_HPP

#include "AbstractFileUtil.hpp"
#include "FileUtil.hpp"
#include "FileUtil_CPlusPlus.hpp"
#include "FileUtil_C.hpp"
#include "FileUtil_CSharp.hpp"
#include "FileUtil_Java.hpp"
#include "FileUtil_DependencyGraph.hpp"
#include "FileUtil_CallGraph.hpp"

class FileFactory {
public:
	enum FileType {
		CallGraph,
		DependencyGraph,
		C,
		CPlusPlus,
		CSharp,
		Java
	};

	// static AbstractFileUtil* createFileUtil(FileType fileType) {
	static AbstractFileUtil* createFileUtil(const std::string& path) {
		// TODO:  instead of using enum for filetype, have method determine file type from file name and/or file content
		FileType fileType = C;

		switch(fileType) {
			case CallGraph:
				return new FileUtil_CallGraph();
			case DependencyGraph:
				return new FileUtil_DependencyGraph();
			case C:
				return new FileUtil_C();
			case CPlusPlus:
				return new FileUtil_CPlusPlus();
			case CSharp:
				return new FileUtil_CSharp();
			case Java:
				return new FileUtil_Java();
		}

		// format not supported, use default
		return new FileUtil();
	}

};

#endif