#include<iostream>
#include<vector>
#include<cmath>
#include<algorithm>
#include<string>
#include<set>
using namespace std;

char alpha[256];

int calc(vector<string> &a, vector<char> &letters, vector<int> &d) {
	int m = letters.size();
	int sum = 0;

	//해당 알파벳에 해당하는 인덱스에 점수를 부여해준다.
	for (int i = 0; i < m; i++) {
		alpha[letters[i]] = d[i];
	}
	//한 단어씩 한글자씩 점수를 추가시키고 sum에다가 더해준다.
	for (string s : a) {
		int now = 0;
		for (char x : s) {
			now = now * 10 + alpha[x];
		}
		sum += now;
	}
	return sum;
}

int main(){
	int n; cin >> n;
	vector<string> v(n); 
	//먼저 더할 단어들을 받아온다.
	for (int i = 0; i < n; i++) {
		cin >> v[i];
	}
	//문자의 종류들을 중복없이 가져온다.
	set<char> letters;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < v[i].size(); j++) {
			letters.insert(v[i][j]);
		}
	}
	
	//그리고 문자들에게 가장 높은 9부터 쭉 주기 위해
	//d배열에 숫자를 부여한다.
	vector<int> d;
	int m = letters.size();
	for (int i = 9; i > 9 - m; i--) {
		d.push_back(i); //제일 큰거 부터
	}
	//제일 작은것부터 큰걸로 정렬해야 next_permutation이 된다.
	sort(d.begin(), d.end());
	//그리고 인덱스를 사용하기 위해 vector에 복사한다.
	vector<char> letterv;
	set<char>::iterator it;
	for (it = letters.begin(); it != letters.end(); ++it) {
		char c= *it;
		letterv.push_back(c);
	}
	//next_permutation을 하면서 점수가 최대일때를 찾아준다.
	int ans = 0;
	do {
		int now = calc(v, letterv, d);
		if (ans < now) {
			ans = now;
		}
	} while (next_permutation(d.begin(), d.end()));
	cout << ans << "\n";

	return 0;
}
