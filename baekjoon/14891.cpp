#include <iostream>
#include <vector>
#include <string>
using namespace std;
//어떻게 차례대로 움직이게 만드냐가 관건 -> 단계마다 어떤 톱니바퀴가 돌아갈지 미리 생각해 둔다.

int main() {
	int n = 4;
	vector<string> a(n);
	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}
	int k;
	cin >> k;
	while (k--) {
		int no, dir;
		cin >> no >> dir; //톱니 번호와 방향
		no -= 1; //1번째는 배열의 0번째 있으므로
		vector<int> d(n);
		d[no] = dir;
		//회전하는 톱니의 왼쪽 톱니부터 1번째 톱니까지 계산을 하면
		for (int i = no - 1; i >= 0; i--) {
			if (a[i][2] != a[i + 1][6]) { 
				//왼쪽 톱니의 2번톱니와 6번톱니가 맡닿는다 그 값이 다르면 반대방향으로 회전
				d[i] = -d[i + 1];
			}
			else {
				//왼쪽 톱니가 회전하지 않으면 나머지도 회전하지 않으므로
				break;
			}
		}
		//회전하는 톱니의 오른쪽 톱니부터 4번째 톱니까지 계산을 하면
		for (int i = no + 1; i < n; i++) {
			if (a[i - 1][2] != a[i][6]) {
				d[i] = -d[i - 1];
			}
			else break;
		}
		for (int i = 0; i < n; i++) {
			if (d[i] == 0) continue; //0이면 회전하지 않는다.
			if (d[i] == 1) {
				//시계방향 회전
				char temp = a[i][7];
				for (int j = 7; j >= 1; j--) {
					a[i][j] = a[i][j - 1];
				}
				a[i][0] = temp;
			}
			else if (d[i] == -1) {
				//반시계방향 회전
				char temp = a[i][0];
				for (int j = 0; j < 7; j++) {
					a[i][j] = a[i][j+1];
				}
				a[i][7] = temp;
			}
		}
	}
	int ans = 0;
	for (int i = 0; i < n; i++) {
		if (a[i][0] == '1') {
			//cout << i<<": "<<a[i][0] << endl;
			ans |= (1 << i);
		}
	}
	cout << ans << '\n';
	return 0;
}	
