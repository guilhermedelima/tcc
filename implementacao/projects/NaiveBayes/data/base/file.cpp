#include <fstream>
#include <iostream>
#include <string>
#include <string.h>
#include <stdlib.h>

#define MAX 100000

using namespace std;

int main(int argc, char **argv){

	if( argc != 2 ){
		cerr << "usage: " << argv[0] << " <TEXT_FILE>" << endl;
		exit(-1);
	}

	ios::sync_with_stdio(false);

	string line;
	char positive_str[100];
	char negative_str[100];

	strcpy(positive_str, "positive_");
	strcpy(negative_str, "negative_");

	strcat(positive_str, argv[1]);
	strcat(negative_str, argv[1]);

	ifstream ifs(argv[1]);
	ofstream pfs(positive_str);
	ofstream nfs(negative_str);

	while( getline(ifs, line) ){
		if( line[0] == 'P' && line[1] == ' ' )
			pfs << line.substr(2) << endl;
		else if( line[0] == 'N' && line[1] == ' ' )
			nfs << line.substr(2) << endl;
		else
			cout << line << endl;
	}

	ifs.close();
	pfs.close();
	nfs.close();

	return 0;
}

