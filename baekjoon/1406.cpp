/*
L: Ŀ���� �������� ��ĭ �ű�
D: Ŀ���� ���������� ��ĭ �ű�
B: Ŀ�� ���ʿ� �ִ� ���ڸ� ������
P $:$��� ���ڸ� Ŀ�� ���ʿ� �߰���
�ʱ⿡ �����⿡ �ԷµǾ� �ִ� ���ڿ��� �־�����, �� ���� �Է��� ��ɾ
���ʷ� �־����� ��, ��� ��ɾ �����ϰ� �� �� �����⿡ �ԷµǾ� �ִ�
���ڿ��� ���ϴ� ���α׷�. �� ��ɾ ����Ǳ� ���� Ŀ���� ���� �� �ڿ� ��ġ.
(1) �迭�� ���ڿ� �Է�
char cs[20];
cin.getline(cs, 20);
���� ���� ����ϸ� �Է¹��� ���ڿ��� cs �迭�� ���Եȴ�.
���⼭ 20�� �̻��� ���ڿ��� �Է¹�����, 20������ �Է¹ްԵȴ�.
������� �����Ͽ� �Է¹޴´�.
(2) string�� ���ڿ� �Է�
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

	list<char> editor(s.begin(), s.end()); // ���ڿ��� ����Ʈ�� �ٲ��ش�.  
	auto cursor = editor.end(); // �ʱ� Ŀ���� ������ ���� �ڷ� �Ѵ�.
	
	while (n--) {
		char cmd;
		cin >> cmd; //��ɾ� �Է� 

		if (cmd == 'L') { //begin�� �ƴϸ� Ŀ�� ��������
			if (cursor != editor.begin()) {
				cursor--;
			}
		}
		else if (cmd == 'D') { //������ ���� �ڰ� �̴ϸ� Ŀ�� ����������
			if (cursor != editor.end()) {
				cursor++;
			}
		}
		else if (cmd == 'B') { //Ŀ�� �������� �̵��� ���� �� �ٽ� ���������� �ٲ��ش�.
			if (cursor != editor.begin()) {
				cursor--;
				editor.erase(cursor++);
				
			}
		}
		else if (cmd == 'P') { //�׳� �־��ָ� �ȴ�.
			char x;
			cin >> x;
			editor.insert(cursor, x);
		}
	}
	for (auto &x : editor) { //��� ���
		cout << x;
	}
	return 0;
}

