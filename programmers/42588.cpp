#include <string>
#include <vector>
#include <iostream>
using namespace std;

vector<int> solution(vector<int> heights) {
	
	int last = int(heights.size())-1;
	vector<int> answer(last+1);
	for (int i = last; i >= 0; i--) {
		for (int j = i - 1; j >= 0; j--) {
			if (heights[i] < heights[j]) {
				answer[i] =j+1;
				break;
			}
		}
	}

	return answer;
}

int main() {
	vector<int> ex = { 6,9,5,7,4 };
	vector<int> answer = solution(ex);
	for (int i = 0; i < answer.size(); i++) {
		cout << answer[i] << endl;
	}

	return 0;
}
