#include <string>
#include<map>
#include <vector>
#include<iostream>

using namespace std;

string solution(vector<string> participant, vector<string> completion) {
	string answer = "";
	map<string, int > m;
	for (int i = 0; i < participant.size(); i++) {
		if (m.find(participant[i]) == m.end()) {
			m.insert(make_pair(participant[i], 1));
		}
		else {
			m[participant[i]] += 1;
		}
	}
	for (int i = 0; i < completion.size(); i++) {
		m[completion[i]] -= 1;
	}
	map<string, int>::iterator it = m.begin();
	for (it; it != m.end(); it++) {
		//cout << it->first << endl;
		
		if (it->second != 0) {
			cout << it->first << endl;
			answer.append(it->first);
			break;
		}
	}
    return answer;
}
