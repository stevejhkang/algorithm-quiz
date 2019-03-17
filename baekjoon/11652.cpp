#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

int main() {
	int n; scanf("%d", &n);
	vector<long long> v;
	//�Է¹޴� ���ڰ� -2^62 ~ 2^62���� �� 8*8����Ʈ�� long long���� ����� �ȴ�.
	for (int i = 0; i < n; i++) {
		long long a;
		scanf("%lld", &a);
		//printf("%lld", a);
		v.push_back(a);
	}

	sort(v.begin(),v.end());
	
	if (v.size() == 1) {
		printf("%lld\n", v[0]);
		return 0;
	}
	int count = 1;
	map<long long, int> countArr; //map�� Ű-���� �����͸� �����ϴ� �ߺ����� �ڷᱸ���̴�.
	countArr.insert(pair<long long, int>(v[0], count));
	for (int i = 1; i < n; i++) {
		if (v[i] == v[i - 1]) { //������ ������ ++���ش�.
			count++;
			if (i == n - 1) {
				countArr[v[i]] = count;
			}
		}
		if (v[i] != v[i - 1]) { //������ �ٸ��� �������� ����ī��Ʈ�� ���ش�.
			countArr[v[i-1]] = count;
			count = 1;
		}
	}
	long long max = 0; long long number=0;
	
	//auto max = max_element(countArr.begin(), countArr.end(),countArr.value_comp());
	for (auto it = countArr.begin(); it != countArr.end(); it++) {
		if (it->second > max) {
			max = it->second;
			number = it->first;
		}
		//printf("%lld\n", it->first);
	}
	printf("%lld\n", number);
	return 0;
}