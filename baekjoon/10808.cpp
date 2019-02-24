#include <iostream>
#include <string>
using namespace std;
int alpha[26];
int main()
{
	string S;
	cin >> S;
	
	for (int i = 0; i < S.size(); i++) {
		alpha[S.at(i)-97]++;
		
	}
	for (int i = 0; i < 26; i++) {
		cout << alpha[i] << ' ';
	}
	
	
	
	return 0;
}
