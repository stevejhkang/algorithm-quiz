#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
#include <queue>
#include <map>
#include <string>
using namespace std;

int start = 0; //처음 숫자 만들기 위한 int
const int GOAL = 123456789;

map<int, int> visited; //<key,value>의 타입이고 map은 set과 마찬가지로 중복이 없다.

typedef struct{
	int y, x;
}dir;

dir direction[4] = { {1,0},{-1,0},{0,1},{0,-1} };

int main() {
	
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			int num;
			scanf("%d", &num);
			if (num == 0)
				num = 9;
			start = start * 10 + num; //숫자 배열을 만들기 위해 입력받을 때마다 첫자리 수로 넣어준다.
		}
	}
	queue<int> q; 
	q.push(start);//처음 수를 넣어준다.
	visited[start] = 0;

	while (!q.empty()) {
		int cur = q.front();
		string str = to_string(cur);
		//현재 수를 string으로 바꿔준다. 즉 저장은 int 다룰때는 string
		q.pop();

		if (cur == GOAL) //완성되면 break;
			break;
		int blank = str.find('9'); //비어있는 칸을 찾는다.
		int y = blank / 3; //y축 좌표는 몫이다.
		int x = blank % 3; //x축 좌표는 나머지이다.

		for (int i = 0; i < 4; i++) {
			int nextY = y + direction[i].y;
			int nextX = x + direction[i].x;

			if (0 <= nextY && 0 <= nextX && nextY < 3 && nextX < 3) {
				string temp = str;
				swap(temp[y * 3 + x], temp[nextY * 3 + nextX]);
				//퍼즐이 이동하는 것이랑 똑같게 1차원 배열인 string상에서 위치를 변경하게 만든다.

				int next = stoi(temp); //next값을 int로 바꿔준다.
				if (!visited.count(next)) { //next 원소가 없으면
					//map에선 next키 값을 가지는 원소가 있는지 확인하기 위해 count를 사용한다.
					visited[next] = visited[cur] + 1;
					q.push(next);
				}
			}
		}
	}
	if (!visited.count(GOAL))
		printf("-1\n");
	else
		printf("%d\n", visited[GOAL]);
	return 0;
}