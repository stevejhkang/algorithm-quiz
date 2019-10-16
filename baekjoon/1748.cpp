#include <string>
#include <vector>
#include<iostream>
#include<cmath>
#include<algorithm>
using namespace std;

int main() {
	string n; cin >> n;
	int len = n.size();
	//cout << len << endl;
	int answer = 0;
	for (int i = 1; i <= len; i++) {
		if (i == len) {//마지막 자리일 때
			int num = atoi(n.c_str());
			//cout << (num+1 - pow(10, i - 1))*i << endl;
			answer += (num+1 - pow(10,i - 1))*i;
			continue;
		}
		int jarihap= pow(10,i) - pow(10,i-1);
		//cout << jarihap*i << endl;
		answer += jarihap*i;
	}
	cout << answer << endl;

	return 0;
}
