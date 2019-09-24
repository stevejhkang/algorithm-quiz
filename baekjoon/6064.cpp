#include <string>
#include <vector>
#include<iostream>
#include<cmath>
#include<algorithm>
using namespace std;

int GCD(int a, int b) {
	while (a%b != 0) {
		int r = a % b;
		a = b;
		b = r;
	}
	return b;
}
int LCM(int a, int b) {
	return (a*b) / GCD(a, b);
}

int main() {
	int t; cin >> t;
	while (t--) {
		int m, n, x, y;
		cin >> m >> n >> x >> y;
	
		int max_num = LCM(m, n);
		//cout << LCM(m, n) << endl;
		while (1) {
			if ((x - 1) % n == y - 1 || x > max_num) {
				break;
			}
			x += m;
		}
		if (x > max_num)
			cout << -1 << "\n";
		else
			cout << x << "\n";
	}
	return 0;
}
