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
			dp[i] = dp[i - 1]% 1000000;//0�� �ƴϱ⸸ �ϸ� ���� dp�� �����´�.
			//cout << i<<": "<<dp[i] << endl;
		}

		// ������ int���� ��������� ���ϸ� �̻��� ������ �ٲ�
		// ���� (int)�� ���ָ� �ƽ�Ű�ڵ� ���� intŸ������ �ٲ���.
		//if (i > 0)cout << (str.at(i - 1)-'0')*10+(str.at(i)-'0') << endl;
		if ((str.at(i - 1) - '0') * 10 + (str.at(i) - '0') >= 10
			&& (str.at(i - 1) - '0') * 10 + (str.at(i) - '0') <= 26) {
			dp[i] = (dp[i - 2] + dp[i]) % 1000000; 
			//���ڸ� ��찡 �����ϴٸ� ����� ���� �߰��Ǿ�� �ϴµ�
			//�߰��Ǵ� ����� ���� ���ڸ� ���� ���� dp[i-2]�̴�.
			//cout << i << ": " << dp[i] << endl;
		}
	}

	printf("%d\n", dp[str.size()-1]);
	return 0;
}