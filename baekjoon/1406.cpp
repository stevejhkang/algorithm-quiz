/*
L: 커서를 왼쪽으로 한칸 옮김
D: 커서를 오른쪽으로 한칸 옮김
B: 커서 왼쪽에 있는 문자를 삭제함
P $:$라는 문자를 커서 왼쪽에 추가함
초기에 편집기에 입력되어 있는 문자열이 주어지고, 그 이후 입력한 명령어가
차례로 주어졌을 때, 모든 명령어를 수행하고 난 후 편집기에 입력되어 있는
문자열을 구하는 프로그램. 단 명령어가 수행되기 전에 커서는 문장 맨 뒤에 위치.
(1) 배열에 문자열 입력
char cs[20];
cin.getline(cs, 20);
위와 같이 사용하면 입력받은 문자열이 cs 배열에 대입된다.
여기서 20개 이상의 문자열을 입력받으면, 20까지만 입력받게된다.
공백또한 포함하여 입력받는다.
(2) string에 문자열 입력
string cpps;
getline(cin, cpps);
*/

#include<iostream>
#include<string>
#include<list>
using namespace std;

int main() {
	int n; string s; 
	cin >> s; cin >> n;

	list<char> editor(s.begin(), s.end()); // 문자열을 리스트로 바꿔준다.  
	auto cursor = editor.end(); // 초기 커서는 마지막 문자 뒤로 한다.
	
	while (n--) {
		char cmd;
		cin >> cmd; //명령어 입력 

		if (cmd == 'L') { //begin이 아니면 커서 왼쪽으로
			if (cursor != editor.begin()) {
				cursor--;
			}
		}
		else if (cmd == 'D') { //마지막 문자 뒤가 이니면 커서 오른쪽으로
			if (cursor != editor.end()) {
				cursor++;
			}
		}
		else if (cmd == 'B') { //커서 왼쪽으로 이동후 삭제 후 다시 오른쪽으로 바꿔준다.
			if (cursor != editor.begin()) {
				cursor--;
				editor.erase(cursor++);
				
			}
		}
		else if (cmd == 'P') { //그냥 넣어주면 된다.
			char x;
			cin >> x;
			editor.insert(cursor, x);
		}
	}
	for (auto &x : editor) { //모두 출력
		cout << x;
	}
	return 0;
}

