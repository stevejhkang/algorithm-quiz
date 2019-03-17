#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <vector>
using namespace std;
/* 1. 합병정렬(Merge Sort)는 분할 정복 방식으로 설계된 알고리즘이다.
입력으로 하나의 배열을 받고, 계속 두개의 배열로 쪼개 나간 뒤, 합치면서 정렬해 최후에는 하나의 정렬을 출력한다.*/
//Merge
void merge(vector<int>& v, int start, int end, int mid) {
	vector<int> tmp;
	int i = start, j = mid + 1, copy = 0;
	//결과를 저장할 배열에 하나씩 비교하여 저장한다.
	while (i <= mid && j <= end) {
		if (v[i] < v[j]) tmp.push_back(v[i++]);
		else if (v[i] > v[j])tmp.push_back(v[j++]);
	}
	//이제 남은 값들을 채워넣어 준다.
	//v[i]와 v[j]안에선 이미 정렬이 되어 있으므로 그대로 넣어준다.
	while (i <= mid) tmp.push_back(v[i++]);
	while (j <= end)tmp.push_back(v[j++]);
	//원래 배열에 복사해준다.
	for (int k = start; k <= end; k++) {
		v[k] = tmp[copy++];
	}
}
//Merge Sort
void mergeSort(vector<int>&v, int start, int end) {
	if (start < end) {
		int mid = (start + end) / 2;
		//divide
		mergeSort(v, start, mid); //divide from start to mid. mergeSort(v,0,1) 
		mergeSort(v, mid + 1, end); //divide from mid+1 to end. mergeSort(v,2,3)
		//재귀적으로 divide되서 마지막에 
		//conquer merge(v,0,1,0)에서부터 merge를 시작한다.
		merge(v, start, end, mid);
	}
}
/* 2. 퀵소트(Quick Sort) 또한 분할 정복을 이용하여 정렬을 수행하는 알고리즘이다. pivot point라는 
기준이 되는 값을 하나 설정 하는데, 이 값을 기준으로 작은 값은 왼쪽, 큰 값은 오른쪽으로 옮기는 방식으로 정렬한다.
피벗을 제외한 왼쪽 리스트와 오른쪽 리스트를 다시 정렬한다.
분할된 배열의 크기가 1이되면 배열이 모두 정렬된 것이다.
*/
// 10 4 8 2 3 9 7 1 6 10 5
void quickSort(vector<int>&v, int start, int end) {
	int ppoint = (start + end) / 2;
	int pivot = v[ppoint];
	int i = start, j = end;
	while (i <j) {
		//pivot값보다 왼쪽 값이 작으면 계속 간다.
		while (pivot > v[i] && i < j ) {
			if(i<ppoint)i++;
		}
		//pivot값보다 오른쪽 값이 크면 계속 간다.  
		while (pivot < v[j] && i < j) {
			j--;
		}
		if(i<j) swap(v[i], v[j]);
	} 
	
	if(start<j-1) quickSort(v, start, j-1);
	if(j+1<end)quickSort(v, j+1, end);

}

int main() {

	int n; scanf("%d\n", &n);
	vector <int> v;
	for (int i = 0; i < n; i++) {
		int m;
		scanf("%d", &m);
		v.push_back(m);
	}
	quickSort(v, 0, v.size()-1);
	for (int i = 0; i < n; i++) {
		printf("%d\n", v[i]);
	}

	return 0;
}