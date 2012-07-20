//--------------------------------------------------------------------------  //
//
// FILE NAME       :  X937out.cpp
//
// DESCRIPTIVE NAME:  Provides X937 Output Plug-in APIs
//
// AUTHOR          :  Srinivasan MS
//
// MODULE TYPE     :  C++ Source File
//
//--------------------------------------------------------------------------  //
http://www.codeguru.com/Cpp/Cpp/algorithms/combinations/article.php/c7605/

#include<iostream>
using namespace std;

long long totCount ;

// this function finds the other
// permutations by rotating the string
// in circular manner

void circularPrint(char *str) {

    int  len = strlen(str);
    char *mystr = new char [len +1];
    int i,j;

    for(i=0; i <len; i++ ) {
        for(j=0;j<len; j++ )
            mystr[j] = str[(i+j)%len];
        mystr[j] =0;
	totCount++;
        // comment the line below to supress the string output
        cout << mystr  << endl;

    }

    delete []mystr;
    return ;
}


void permutation(char prefix[],char suffix[])
{
  int length=strlen(suffix);
  int len = strlen(prefix) ;
  int i=0, j=0;
  char *myprefix = new char [len];

  if(len ) {
    strncpy(myprefix, prefix, len - 1 );
    myprefix[len-1]= 0;

    for(i=0; i< length ; i++) {

  	char *mysuffix = new char [length+2];
        mysuffix[0] = prefix[len-1];

        // rotate the current append and prepare append for next
        for(j=0; j< length ; j++ ) {
            mysuffix[j+1] = suffix[(i+j)%length];
        }

        mysuffix [j+1]= 0;
        permutation(myprefix, mysuffix);
        delete []mysuffix;
    }
  }
  else {
    // found permutation, now find other
    // permutations by rotating the string
    circularPrint(suffix);
  }
  delete []myprefix;
}


int main()
{

  char prefix[]="12345"; // first N-1 characters of the string of length N
  char suffix[]="6";     // last character of the string
  time_t t1, t2;

  cout << "String is " << prefix << suffix <<
        "  Length is " << strlen(prefix) +strlen(suffix) <<  endl;

  cout << "Permutations are listed below " << endl;
  // start time
  t1 = time(NULL);

  // find permutation
  permutation(prefix, suffix);

  // end time
  t2 = time(NULL);

  // display timings
  cout<<"Completed in " << t2-t1 << "(secs) - No of combinations are "<< ":" << totCount << endl;

  return 0;
}

