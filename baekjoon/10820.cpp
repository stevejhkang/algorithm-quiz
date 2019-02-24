#include <iostream>
#include<string>
#include<cstdio>
#include <vector>
using namespace std;

int answer[4] = { 0 }; //소문자, 대문자, 숫자, 공백

int main() {
	string cs;

	
	//getline함수는 EOF에 도달하거나 유효하지 않은 입력이 발생하면 
	//getline은 false문을 반환한다.
	while (getline(cin,cs,'\n')) {
	
		for (int i = 0; i < cs.size(); i++) {
			
			//cout << cs[i] << " ";
			
			if (cs[i] >= 'a'&&cs[i] <= 'z') answer[0]++;
			else if (cs[i] >= 'A'&&cs[i] <= 'Z') answer[1]++;
			else if (cs[i] >= '0'&&cs[i] <= '9') answer[2]++;
			else answer[3]++;
			//printf("%2c", cs[m]);
			//printf("\n");
			
			
			
			
		}
		cout << answer[0] << " " << answer[1] << " " << answer[2] << " " << answer[3] << "\n";
		fill_n(answer, 4, 0);
	}

	
	return 0;
}