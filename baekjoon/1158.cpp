#include<iostream>
#include<string>
#include<list>
using namespace std;

int main() {

	int n, m;
	cin >> n >> m;
	list<int> _list;
	for (int i = 1; i <= n; i++) {
		_list.push_back(i);
	}
	auto _iter = _list.begin();
	cout << "<";
	while (!_list.empty()) {
		
		for (int i = 1; i < m; i++) {
			if (_iter == _list.end()) {
				_iter = _list.begin();
				_iter++;
			}
			else _iter++;
		}
		
		if (_iter == _list.end()) {
			_iter = _list.begin();
		}
		if(_list.size()!=1)cout << *_iter <<", ";
		else cout << *_iter;
		
		_list.erase(_iter++);
	}
	cout << ">" << endl;

}
