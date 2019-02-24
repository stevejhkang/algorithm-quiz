#include <iostream>
#include <list>
#include <string>
using namespace std;

int main() {
	int n;
	string m;
	list<int> dequeue;
	cin >> n;
	while (n--) {
		cin >> m;
		int x;
		//push_front X : 정수 X를 덱의 앞에 넣는다.
		if (m == "push_front") {
			cin >> x;
			dequeue.push_front(x);
		}
		//push_back X: 정수 X를 덱의 뒤에 넣는다.
		else if (m == "push_back") {
			cin >> x;
			dequeue.push_back(x);
		}
		//pop_front: 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
		else if (m == "pop_front") {
			if (!dequeue.empty()) {
				cout << dequeue.front()<<endl;
				dequeue.pop_front();
			}
			else cout << -1<<endl;
		}
		//pop_back: 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
		else if (m == "pop_back") {
			if (!dequeue.empty()) {
				cout << dequeue.back() << endl;
				dequeue.pop_back();
			}
			else cout << -1<<endl;
		}
		//size: 덱에 들어있는 정수의 개수를 출력한다.
		else if (m == "size") {
			if (!dequeue.empty()) cout<<dequeue.size()<<endl;
			else cout << 0 << endl;;
		}
		//empty: 덱이 비어있으면 1을, 아니면 0을 출력한다.
		else if (m == "empty") {
			if (!dequeue.empty()) cout << 0<<endl;
			else cout << 1 << endl;;
		}
		//front: 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
		else if (m == "front") {
			if (!dequeue.empty()) {
				cout << dequeue.front() << endl;
			}
			else cout << -1<<endl;
		}
		//back: 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
		else if (m == "back") {
			if (!dequeue.empty()) {
				cout << dequeue.back() << endl;;
			}
			else cout << -1 << endl;;
		}
	}
}