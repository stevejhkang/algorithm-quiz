#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <string>
#include <iostream>
using namespace std;

int main() {

	string str; getline(cin, str);
	int dp[5002];
	fill_n(dp, 5002, 0);
	str = ' ' + str;
	//#include <typeinfo>
	//cout << typeid(str.at(0) * 10 + str.at(1)).name() << endl;
	dp[0] = 1;
	for (int i = 1; i < str.size(); i++) {
		
		if (str.at(i) > '0') {
			dp[i] = dp[i - 1]% 1000000;//0이 아니기만 하면 이전 dp를 가져온다.
			//cout << i<<": "<<dp[i] << endl;
		}

		// 각각은 int값을 출력하지만 더하면 이상한 값으로 바뀜
		// 또한 (int)로 해주면 아스키코드 값을 int타입으로 바꿔줌.
		//if (i > 0)cout << (str.at(i - 1)-'0')*10+(str.at(i)-'0') << endl;
		if ((str.at(i - 1) - '0') * 10 + (str.at(i) - '0') >= 10
			&& (str.at(i - 1) - '0') * 10 + (str.at(i) - '0') <= 26) {
			dp[i] = (dp[i - 2] + dp[i]) % 1000000; 
			//두자리 경우가 가능하다면 경우의 수가 추가되어야 하는데
			//추가되는 경우의 수는 두자리 전의 수인 dp[i-2]이다.
			//cout << i << ": " << dp[i] << endl;
		}
	}

	printf("%d\n", dp[str.size()-1]);
	return 0;
}