#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
#include <cmath>
using namespace std;

int main() {
	//(1 ≤ N ≤ 100,000)
	int n; scanf("%d", &n);
	int dp[100001];
	//7= 4+1+1+1
	//11,22,33,44,55,66
	
	for (int i = 1; i <= n; i++) {
		if (i*i == n) {
			printf("%d\n", 1); return 0;
		}
	}
	for (int i = 1; i <= n; i++) {
		if (dp[i] != 1) { dp[i] = i; } 
		if (pow(i,2) < 100000) { dp[i*i] = 1; } //정수와 정수끼리 비교한다고 생각하기때
		
		if (i == 1||i==2||i==3) {
			continue;
		}
		else if(i>=4) {
			for(int j = i - 1; j >= (i/2)-1; j--) {	//7=6~3
				if (dp[i] > dp[j] + dp[i - j]) dp[i] = dp[j] + dp[i - j];
			}
		}
	}
	// 1=1,2=1+1,3=(1+1+1,2+1),4=(4,3+1,2+2),5=(4+1,3+2),6=(5+1,4+2,3+3,2+4),7=(6+1,5+2,4+3,3+4) 
	// 16=16,0/15,1/14,2/13,3/12,4/11,5/10,6/9,7/8,8/
	//12=(4+4)+(4)
	//dp[i]=min(dp[i-1]+dp[1],...,dp[(i/2)+1]+dp[(i/2)+1])
	
	
	printf("%d\n", dp[n]);

	return 0;
}