#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <vector>
using namespace std;
/* 1. �պ�����(Merge Sort)�� ���� ���� ������� ����� �˰����̴�.
�Է����� �ϳ��� �迭�� �ް�, ��� �ΰ��� �迭�� �ɰ� ���� ��, ��ġ�鼭 ������ ���Ŀ��� �ϳ��� ������ ����Ѵ�.*/
//Merge
void merge(vector<int>& v, int start, int end, int mid) {
	vector<int> tmp;
	int i = start, j = mid + 1, copy = 0;
	//����� ������ �迭�� �ϳ��� ���Ͽ� �����Ѵ�.
	while (i <= mid && j <= end) {
		if (v[i] < v[j]) tmp.push_back(v[i++]);
		else if (v[i] > v[j])tmp.push_back(v[j++]);
	}
	//���� ���� ������ ä���־� �ش�.
	//v[i]�� v[j]�ȿ��� �̹� ������ �Ǿ� �����Ƿ� �״�� �־��ش�.
	while (i <= mid) tmp.push_back(v[i++]);
	while (j <= end)tmp.push_back(v[j++]);
	//���� �迭�� �������ش�.
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
		//��������� divide�Ǽ� �������� 
		//conquer merge(v,0,1,0)�������� merge�� �����Ѵ�.
		merge(v, start, end, mid);
	}
}
/* 2. ����Ʈ(Quick Sort) ���� ���� ������ �̿��Ͽ� ������ �����ϴ� �˰����̴�. pivot point��� 
������ �Ǵ� ���� �ϳ� ���� �ϴµ�, �� ���� �������� ���� ���� ����, ū ���� ���������� �ű�� ������� �����Ѵ�.
�ǹ��� ������ ���� ����Ʈ�� ������ ����Ʈ�� �ٽ� �����Ѵ�.
���ҵ� �迭�� ũ�Ⱑ 1�̵Ǹ� �迭�� ��� ���ĵ� ���̴�.
*/
// 10 4 8 2 3 9 7 1 6 10 5
void quickSort(vector<int>&v, int start, int end) {
	int ppoint = (start + end) / 2;
	int pivot = v[ppoint];
	int i = start, j = end;
	while (i <j) {
		//pivot������ ���� ���� ������ ��� ����.
		while (pivot > v[i] && i < j ) {
			if(i<ppoint)i++;
		}
		//pivot������ ������ ���� ũ�� ��� ����.  
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