#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <algorithm>
#include<vector>
using namespace std;

long long f[21]; //각 자리수의 팩토리얼수 저장
bool check[21]; //1~N까지의 숫자가 순열에 있는지 확인하는 변수

int main() {
	int n;
	scanf("%d", &n); //자리수 정한다.

	//핵심은 그 수까지 올라가는데 몇번에 몇자리수에서의 팩토리얼들이 있었는가를 세는 것이다.
	f[0] = 1;
	for (int i = 1; i < 21; i++) f[i] = f[i - 1] * i; //각 자리수의 팩토리얼 값을 저장한다.
	int select; 
	scanf("%d", &select); 
	if (select == 1) { //1: times를 입력받고 times번째 수열을 출력
		int a[21];
		long long times; scanf("%lld", &times);
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= n; j++) {
				if (check[j] == true) continue;
				if (f[n - 1 - i] < times){
					//팩토리얼 값이 times보다 작으면 times에서 팩토리얼 값을 빼준다.
					//여러번 뺄 수도 있다.
					times -= f[n - 1 - i];
				}
				else {//팩토리얼 값이 times보다 크면 해당 원소의 숫자를 찾은 것.
					//예: 4321이면 1,2,3번의 팩토리얼들을 빼준다. 마지막 4에서 작아짐.
					a[i] = j; 
					check[j] = true;
					//a[i]에 해당 숫자를 저장하고 중복을 제거하기위해 check을 true해준다.
					break;//다음 자리수 찾는다.
				}
			}
		}
		for (int i = 0; i < n; i++) printf("%d ", a[i]);
		printf("\n");
	}
	else if (select == 2) { //2: 수열을 입력받고 몇번째인지를 출력
		int b[21]; 
		for (int i = 0; i < n; i++) {
			scanf("%d", &b[i]);
		}
		long long ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < b[i]; j++) {
				//1부터 해당하는 원소까지 팩토리얼 값을 더해준다.
				//대신 이미 사용하지 않는 값만 더해줘야한다.
				//예를 들어 1234에서 3이 있는 자리수는 이미 1,2를 사용했으므로 팩토리얼을 한번만 더하게 될 것이다.
				if (check[j] == false) 
					ans += f[n - i - 1];
			}
			check[b[i]] = true; //이미 사용한 값은 true처리 해준다.
		}
		printf("%lld ", ans + 1);
	}
	return 0;
}
