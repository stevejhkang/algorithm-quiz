#include <iostream>
#include <string>
using namespace std;

int alpha[26];
int main() {
	fill_n(alpha, 26, -1);

	string S;
	cin >> S;

	for (int i = 0; i < S.size(); i++) {
		if (alpha[S.at(i) - 97] == -1) alpha[S.at(i) - 97] = i; 
	}
	for (int i = 0; i < 26; i++) cout << alpha[i] << ' ';
}