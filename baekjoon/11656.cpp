#include <bits/stdc++.h>

using namespace std;

int main() {
	string s;
	string str[1001];

	cin >> s;
	for (int i = 0; i<s.length(); i++) {
		str[i] = s.substr(i);
	}
	int size = s.length();
	sort(str, str + size);
	for (int i = 0; i<s.length(); i++) {
		cout << str[i] << '\n';
	}
	return 0;
}
