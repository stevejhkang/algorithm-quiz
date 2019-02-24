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
		//push_front X : ���� X�� ���� �տ� �ִ´�.
		if (m == "push_front") {
			cin >> x;
			dequeue.push_front(x);
		}
		//push_back X: ���� X�� ���� �ڿ� �ִ´�.
		else if (m == "push_back") {
			cin >> x;
			dequeue.push_back(x);
		}
		//pop_front: ���� ���� �տ� �ִ� ���� ����, �� ���� ����Ѵ�. ����, ���� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
		else if (m == "pop_front") {
			if (!dequeue.empty()) {
				cout << dequeue.front()<<endl;
				dequeue.pop_front();
			}
			else cout << -1<<endl;
		}
		//pop_back: ���� ���� �ڿ� �ִ� ���� ����, �� ���� ����Ѵ�. ����, ���� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
		else if (m == "pop_back") {
			if (!dequeue.empty()) {
				cout << dequeue.back() << endl;
				dequeue.pop_back();
			}
			else cout << -1<<endl;
		}
		//size: ���� ����ִ� ������ ������ ����Ѵ�.
		else if (m == "size") {
			if (!dequeue.empty()) cout<<dequeue.size()<<endl;
			else cout << 0 << endl;;
		}
		//empty: ���� ��������� 1��, �ƴϸ� 0�� ����Ѵ�.
		else if (m == "empty") {
			if (!dequeue.empty()) cout << 0<<endl;
			else cout << 1 << endl;;
		}
		//front: ���� ���� �տ� �ִ� ������ ����Ѵ�. ���� ���� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
		else if (m == "front") {
			if (!dequeue.empty()) {
				cout << dequeue.front() << endl;
			}
			else cout << -1<<endl;
		}
		//back: ���� ���� �ڿ� �ִ� ������ ����Ѵ�. ���� ���� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
		else if (m == "back") {
			if (!dequeue.empty()) {
				cout << dequeue.back() << endl;;
			}
			else cout << -1 << endl;;
		}
	}
}