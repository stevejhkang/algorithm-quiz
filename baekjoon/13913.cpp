#include<iostream>
#include<algorithm>
#include<queue>
using namespace std;

int visit[100001];
int from[100001];

void print(int n, int m) {
	if (n != m) {
		print(n, from[m]);
	}
	cout << m << ' ';
}

int main() {
	int n, k; cin >> n >> k;
	if (n == k) {
		cout << 0 << '\n';
		cout<<n<<"\n";
		return 0;
	}
	queue<int> q;
	q.push(n); visit[n] = 0;
	while (!q.empty()) {
		int now = q.front(); q.pop();
		if (now == k) {
			break;
		}
		//+1
		if (now + 1 <= 100000 && !visit[now + 1]) { //범위안, 방문x
			//
			q.push(now + 1);
			visit[now + 1] = visit[now]+1;
			from[now + 1] = now; //전 위치를 저장해준다.
		}
		//-1
		if (now - 1 >= 0 && !visit[now - 1] ) {
			q.push(now - 1);
			visit[now - 1] = visit[now] + 1;
			from[now - 1] = now;
		}
		//*2
		if (now * 2 <= 100000 &&now>0 && !visit[now * 2]) {
			q.push(now * 2);
			visit[now * 2] = visit[now] + 1;
			from[now * 2] = now;
		}
	}
	cout << visit[k] << '\n';
	print(n, k);
	cout << '\n';
	return 0;
}

