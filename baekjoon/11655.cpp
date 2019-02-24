#include <iostream>
#include <cstdio>
#include<string>
using namespace std;

int main() {

	string s;

	getline(cin, s);

	for (int i = 0; i < s.size(); i++) {
		if ((s[i] >= 'a'&&s[i] <= 'z'))
			// 소문자 경우 97~122	
			s[i] = (s[i] - 97 + 13) % 26 + 97;
		else if ((s[i] >= 'A'&&s[i] <= 'Z'))
			// 대문자 경우 65~90
			s[i] = (s[i] - 65 + 13) % 26 + 65;
	}
	cout << s;
	
	return 0;
}