#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
#include<vector>
using namespace std;

long long f[21]; //�� �ڸ����� ���丮��� ����
bool check[21]; //1~N������ ���ڰ� ������ �ִ��� Ȯ���ϴ� ����

int main() {
	int n;
	scanf("%d", &n); //�ڸ��� ���Ѵ�.

	//�ٽ��� �� ������ �ö󰡴µ� ����� ���ڸ��������� ���丮����� �־��°��� ���� ���̴�.
	f[0] = 1;
	for (int i = 1; i < 21; i++) f[i] = f[i - 1] * i; //�� �ڸ����� ���丮�� ���� �����Ѵ�.
	int select; 
	scanf("%d", &select); 
	if (select == 1) { //1: times�� �Է¹ް� times��° ������ ���
		int a[21];
		long long times; scanf("%lld", &times);
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= n; j++) {
				if (check[j] == true) continue;
				if (f[n - 1 - i] < times){
					//���丮�� ���� times���� ������ times���� ���丮�� ���� ���ش�.
					//������ �� ���� �ִ�.
					times -= f[n - 1 - i];
				}
				else {//���丮�� ���� times���� ũ�� �ش� ������ ���ڸ� ã�� ��.
					//��: 4321�̸� 1,2,3���� ���丮����� ���ش�. ������ 4���� �۾���.
					a[i] = j; 
					check[j] = true;
					//a[i]�� �ش� ���ڸ� �����ϰ� �ߺ��� �����ϱ����� check�� true���ش�.
					break;//���� �ڸ��� ã�´�.
				}
			}
		}
		for (int i = 0; i < n; i++) printf("%d ", a[i]);
		printf("\n");
	}
	else if (select == 2) { //2: ������ �Է¹ް� ���°������ ���
		int b[21]; 
		for (int i = 0; i < n; i++) {
			scanf("%d", &b[i]);
		}
		long long ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < b[i]; j++) {
				//1���� �ش��ϴ� ���ұ��� ���丮�� ���� �����ش�.
				//��� �̹� ������� �ʴ� ���� ��������Ѵ�.
				//���� ��� 1234���� 3�� �ִ� �ڸ����� �̹� 1,2�� ��������Ƿ� ���丮���� �ѹ��� ���ϰ� �� ���̴�.
				if (check[j] == false) 
					ans += f[n - i - 1];
			}
			check[b[i]] = true; //�̹� ����� ���� trueó�� ���ش�.
		}
		printf("%lld ", ans + 1);
	}
	return 0;
}
