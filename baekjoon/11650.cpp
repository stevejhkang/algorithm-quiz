#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <vector>
using namespace std;

vector<pair<int,int>> x(100001);


void merge(vector<pair<int, int>>& v, int start, int end, int mid) {
	vector<pair<int,int>> tmp; 
	int i = start, j = mid + 1, copy=0;
	while (i<=mid && j<=end) {
		if (v[i].first < v[j].first) tmp.push_back(v[i++]);
		else if (v[i].first > v[j].first) tmp.push_back(v[j++]);
		else if (v[i].first == v[j].first){
			if (v[i].second < v[j].second) tmp.push_back(v[i++]);
			else tmp.push_back(v[j++]);
		}

	}
	while (i <= mid) tmp.push_back(v[i++]);
	while (j <= end) tmp.push_back(v[j++]);

	for (int k = start; k <= end; k++) {
		v[k] = tmp[copy++];
	}
}

void mergeSort(vector<pair<int, int>>& v, int start, int end) {
	if (start < end) {
		int mid = (start + end) / 2;
		mergeSort(v, start, mid);
		mergeSort(v, mid + 1, end);
		merge(v, start, end, mid);
	}
}

int main() {

	int n; scanf("%d", &n);
	
	for (int i = 0; i < n; i++) {
		scanf("%d %d", &x[i].first,&x[i].second);
	}	
	
	mergeSort(x, 0, n-1);
	
	for (int i = 0; i < n; i++) {
		printf("%d %d\n", x[i].first, x[i].second);
	}


	return 0;
}