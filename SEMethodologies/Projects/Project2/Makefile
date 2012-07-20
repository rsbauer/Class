##
# CodeAnalyzer Make

## Build Executable ##
all : Client

Client : Client.o CodeAnalyzer.o Query.o ParseReport.o FileUtil.o AbstractFileUtil.o FileUtil_CPlusPlus.o FileUtil_C.o FileUtil_CSharp.o FileUtil_Java.o FileUtil_DependencyGraph.o FileUtil_CallGraph.o
	g++ Client.o CodeAnalyzer.o Query.o ParseReport.o FileUtil.o AbstractFileUtil.o FileUtil_CPlusPlus.o FileUtil_C.o FileUtil_CSharp.o FileUtil_Java.o FileUtil_DependencyGraph.o FileUtil_CallGraph.o -o Client

Client.o : Client.cpp
	g++ -c Client.cpp

CodeAnalyzer.o : CodeAnalyzer.cpp CodeAnalyzer.hpp
	g++ -c CodeAnalyzer.cpp

Query.o : Query.cpp Query.hpp ParsedResult.hpp FileFactory.hpp
	g++ -c Query.cpp

FileUtil.o : FileUtil.cpp FileUtil.hpp
	g++ -c FileUtil.cpp

ParseReport.o : ParseReport.cpp ParseReport.hpp FileFactory.hpp
	g++ -c ParseReport.cpp

AbstractFileUtil.o : AbstractFileUtil.cpp AbstractFileUtil.hpp ParsedResult.hpp
	g++ -c AbstractFileUtil.cpp

FileUtil_CPlusPlus.o : FileUtil_CPlusPlus.cpp FileUtil_CPlusPlus.hpp
	g++ -c FileUtil_CPlusPlus.cpp

FileUtil_C.o : FileUtil_C.cpp FileUtil_C.hpp
	g++ -c FileUtil_C.cpp

FileUtil_CSharp.o : FileUtil_CSharp.cpp FileUtil_CSharp.hpp
	g++ -c FileUtil_CSharp.cpp

FileUtil_Java.o : FileUtil_Java.cpp FileUtil_Java.hpp
	g++ -c FileUtil_Java.cpp

FileUtil_DependencyGraph.o : FileUtil_DependencyGraph.cpp FileUtil_DependencyGraph.hpp
	g++ -c FileUtil_DependencyGraph.cpp

FileUtil_CallGraph.o : FileUtil_CallGraph.cpp FileUtil_CallGraph.hpp
	g++ -c FileUtil_CallGraph.cpp

## House Cleaning ##
clean :
	rm -f Client Client.o CodeAnalyzer.o Query.o FileUtil.o ParseReport.o Graph.o SourceCode.o FileUtil.o AbstractFileUtil.o FileUtil_CPlusPlus.o FileUtil_C.o FileUtil_CSharp.o FileUtil_Java.o FileUtil_DependencyGraph.o FileUtil_CallGraph.o
	
## Run Executable ##	
run :
	./Client