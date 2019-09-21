#include <vector>
#include <cstring>

using namespace std;

int MOD = 20170805;

// 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
int solution(int m, int n, vector<vector<int>> city_map) {
    int answer = 0;
    int MOD2 = 20170805;
    int dp[501][501][2];
	memset(dp, 0, sizeof(dp));
	dp[0][0][0] = 1; dp[0][0][1] = 1;
	//0:자유 1:자동차 금지 2: 직진만 가능
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) { //i가 y, j가 x
			if (i == 0 && j == 0) continue;
			if (city_map[i][j] == 1)continue;
			if (i >= 1) dp[i][j][0] += dp[i - 1][j][0]; //위
			if (j>= 1) dp[i][j][1] += dp[i][j - 1][1]; //왼
			if (city_map[i][j] == 0) {
				if (j >= 1)dp[i][j][0] += dp[i][j - 1][1]; //
				if (i >= 1)dp[i][j][1] += dp[i - 1][j][0];
			}
            dp[i][j][0]%=MOD2; dp[i][j][1]%=MOD2;
		}
	}
	answer = (dp[m-2][n-1][0]+dp[m-1][n-2][1])%MOD2;
	return answer;
}
