#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
	vector<int> answer;
	vector<int> days;
	for (int i = 0; i < progresses.size(); i++) {
		int remain = 100 - progresses[i];
		int day = 0;
		if (remain%speeds[i] == 0) {//나머지 0이면
			day = remain / speeds[i];
			days.push_back(day);
		}
		else {
			day = (remain / speeds[i]) + 1;
			days.push_back(day);
		}
	}
	int max_value = days[0]; int cnt = 1;
	for (int i = 1; i < days.size(); i++) {
		if (max_value >= days[i]) {
			cnt++;
		}
		else { //크면
			answer.push_back(cnt);
			cnt = 1;
			max_value = days[i];
		}
		if (i == int(days.size()) - 1) {
			answer.push_back(cnt);
		}
	}

	return answer;
}
