#include <bits/stdc++.h>

using namespace std;

int main() {
	string a, b, c, d;
	cin >> a>>b>>c>>d;
	
	//cout << a.length() << '\n';
	long long e, f;
	e = stoi(a)*pow(10, b.length()) + stoi(b);
	f = stoi(c)*pow(10, d.length()) + stoi(d);
	cout << e + f << '\n';
	return 0;
}
