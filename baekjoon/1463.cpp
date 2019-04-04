#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int N;
int arr[1000001]; 
#define min(X,Y) ((X) < (Y) ? (X) : (Y))  
#define max(X,Y) ((X) > (Y) ? (X) : (Y))

int DP(int n){
	//arr[i]는 정수 i의 연산 최솟값을 저장한다.
	for (int i = 2; i <= n; i++){
		arr[i] = arr[i - 1] + 1; //3. i에서 1을 빼는 경우는 i-1의 연산 최솟값+1이다.
		if (i % 2 == 0) {
			arr[i] = min(arr[i], arr[i / 2] + 1); //2로 나누어 떨어지면 3번 방법과 2번 방법 비교. 연산 값은 2로 나누어 떨어진 수+1
		}
		if (i % 3 == 0){
			arr[i] = min(arr[i], arr[i / 3] + 1); //3으로 나누어 떨어지면 방법 비교. 연산 값은 3으로 나누어 떨어진 수+1ㅓ
		}
	}
	return arr[n];
}

int main(){
	
	arr[1] = 0;
	scanf("%d", &N);
	printf("%d\n", DP(N));
	return 0;
	
}
