#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

int INF = 9876543210;
int MAX = 999900;

int n, m; //�̵��ϰ� ���� ä��, ���峭 ��ư ����
vector<int> broken; //���峭 ��ư�� ����.

bool possible(int num) {
	if (num == 0) { //0�ϰ��
		if (find(broken.begin(), broken.end(), 0) == broken.end())
			return true;
		else return false;
	}
	while(num){//0�� �ƴ� ��� 
		//ù°�ڸ��� �̵����������� üũ�ϰ�
		//��°�ڸ�, �� ���� �ڸ��� ���������� üũ�ϱ� ���� ��� 10���� �����ش�.
		if (find(broken.begin(), broken.end(), num % 10) != broken.end()) return false;
		num /= 10;
	}
	return true;
	//������ �Ǹ� �̵������� ��
}

int main() {
	int n; scanf("%d", &n);
	int m; scanf("%d", &m);
	for (int i = 0; i < m; i++) {
		int broken_b;
		scanf("%d", &broken_b);
		broken.push_back(broken_b);
	}
	//1. ������ ��������
	int oneByone = n - 100;
	if (oneByone < 0) oneByone = -1 * oneByone;  //�����̸� ����� �ٲ��ش�.
	
	//2. �ٸ� ä�η� ������ �̵��ϴ� ���
	//�̵������� ä�� ����� ��� �߿��� 100��ä�ο��� n��ä�η� �̵��ϴ� �ּڰ��� ����Ѵ�.
	int minMove_change = INF; //�ּ��̵� Ƚ�� ����: �ִ����� ���� ����
	int minMove_channel = 0; //�ּ��̵��ϴ� ä��
	for (int i = 0; i < MAX; i++) {
		if (possible(i)) { 
			int times = abs(n - i);
			if (minMove_change > times) {
				//�ּڰ� ����
				minMove_change = times;
				minMove_channel = i;
			}
		}
	}
	int length = 0; //�̵��ϱ� ���� ��ư�� ���� Ƚ��
	if (minMove_channel == 0) length=1;
	while (minMove_channel) {
		minMove_channel /= 10;
		length++;
	}
	int moveChannel = minMove_change + length;

	//1,2�߿��� �ּڰ��� ����ϵ��� �Ѵ�.
	printf("%d\n", min(oneByone, moveChannel));
	return 0;
}