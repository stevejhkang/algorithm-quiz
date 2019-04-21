#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

int INF = 9876543210;
int MAX = 999900;

int n, m; //이동하고 싶은 채널, 고장난 버튼 개수
vector<int> broken; //고장난 버튼을 저장.

bool possible(int num) {
	if (num == 0) { //0일경우
		if (find(broken.begin(), broken.end(), 0) == broken.end())
			return true;
		else return false;
	}
	while(num){//0이 아닐 경우 
		//첫째자리가 이동가능한지를 체크하고
		//둘째자리, 그 다음 자리가 가능한지를 체크하기 위해 계속 10으로 나눠준다.
		if (find(broken.begin(), broken.end(), num % 10) != broken.end()) return false;
		num /= 10;
	}
	return true;
	//끝까지 되면 이동가능한 것
}

int main() {
	int n; scanf("%d", &n);
	int m; scanf("%d", &m);
	for (int i = 0; i < m; i++) {
		int broken_b;
		scanf("%d", &broken_b);
		broken.push_back(broken_b);
	}
	//1. 일일이 돌렸을때
	int oneByone = n - 100;
	if (oneByone < 0) oneByone = -1 * oneByone;  //음수이면 양수로 바꿔준다.
	
	//2. 다른 채널로 돌리고 이동하는 경우
	//이동가능한 채널 모두의 경우 중에서 100번채널에서 n번채널로 이동하는 최솟값을 계산한다.
	int minMove_change = INF; //최소이동 횟수 변수: 최댓값으로 먼저 저장
	int minMove_channel = 0; //최소이동하는 채널
	for (int i = 0; i < MAX; i++) {
		if (possible(i)) { 
			int times = abs(n - i);
			if (minMove_change > times) {
				//최솟값 갱신
				minMove_change = times;
				minMove_channel = i;
			}
		}
	}
	int length = 0; //이동하기 위해 버튼을 누른 횟수
	if (minMove_channel == 0) length=1;
	while (minMove_channel) {
		minMove_channel /= 10;
		length++;
	}
	int moveChannel = minMove_change + length;

	//1,2중에서 최솟값을 출력하도록 한다.
	printf("%d\n", min(oneByone, moveChannel));
	return 0;
}