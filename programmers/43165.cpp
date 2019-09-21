#include <string>
#include <vector>
#include<iostream>
using namespace std;

//int count1;
int ans;

void dfs(vector<int>& numbers, int sum, int depth, int& target) {
	if (depth == numbers.size()) {
		//만약 depth가 배열 사이즈-1과 같고
		//그 합이 target값과 같으면 카운트해준다.
		if (sum == target) {
			//cout << "test" << endl;
			ans++;
		}
		return;
	}
	dfs(numbers, sum + numbers[depth], depth + 1, target);
	dfs(numbers, sum - numbers[depth], depth + 1, target);
}

int solution(vector<int> numbers, int target) {
	int answer = 0;
    //count1=0;
	dfs(numbers, numbers[0], 1, target);
    dfs(numbers,numbers[0]*-1,1,target);
	
	return ans;
}
