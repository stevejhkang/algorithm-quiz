#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <iostream>
#include <vector>
#include <string>
using namespace std;

vector<pair< pair<int , int>, pair<int ,string>>> x(100001); //����, ����, ����, �̸�

void merge(vector<pair< pair<int, int>, pair<int, string>>>& v, int start, int end, int mid) {
	vector<pair< pair<int, int>, pair<int, string>>> tmp;
	int i = start, j = mid + 1, copy = 0;
	while (i <= mid && j <= end) {
		if (v[i].first.first > v[j].first.first) tmp.push_back(v[i++]); //��������
		else if (v[i].first.first < v[j].first.first) tmp.push_back(v[j++]);
		else if (v[i].first.first == v[j].first.first) {
			if (v[i].first.second < v[j].first.second) tmp.push_back(v[i++]); //��������
			else if (v[i].first.second > v[j].first.second) tmp.push_back(v[j++]);
			else if (v[i].first.second == v[j].first.second) {
				if (v[i].second.first > v[j].second.first) tmp.push_back(v[i++]); //��������
				else if (v[i].second.first < v[j].second.first) tmp.push_back(v[j++]);
				else if (v[i].second.first == v[j].second.first) { //������ ���� ������������
					if(v[i].second.second.compare(v[j].second.second)<0) tmp.push_back(v[i++]); //v[i]�� v[j]���� ���� ������ ���϶�
					else if (v[j].second.second.compare(v[i].second.second)<0) tmp.push_back(v[j++]);
				}
			}
		}
	}
	while (i <= mid) tmp.push_back(v[i++]);
	while (j <= end) tmp.push_back(v[j++]);

	for (int k = start; k <= end; k++) {
		v[k] = tmp[copy++];
	}
}

void mergeSort(vector<pair< pair<int, int>, pair<int, string>>>& v, int start, int end) {
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
		cin >> x[i].second.second;
		scanf("%d %d %d", &x[i].first.first,&x[i].first.second,&x[i].second.first);
		
	}
	/*for (int i = 0; i < n; i++) {
	printf("ko: %d, en: %d, math: %d ", x[i].first.first,x[i].first.second,x[i].second.first);
	cout <<"�̸�: "<< x[i].second.second << "\n";
	cout << "--------------------------------------------------------"<<"\n";
	}*/
	mergeSort(x, 0, n - 1);

	for (int i = 0; i < n; i++) {
		//printf("ko: %d, en: %d, math: %d ", x[i].first.first, x[i].first.second, x[i].second.first);
		cout << x[i].second.second << "\n";
	}


	return 0;
}