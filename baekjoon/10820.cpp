#include <iostream>
#include<string>
#include<cstdio>
#include <vector>
using namespace std;

int answer[4] = { 0 }; //�ҹ���, �빮��, ����, ����

int main() {
	string cs;

	
	//getline�Լ��� EOF�� �����ϰų� ��ȿ���� ���� �Է��� �߻��ϸ� 
	//getline�� false���� ��ȯ�Ѵ�.
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