#include <stdio.h>
#include <string.h>
using namespace std;

char str[100001];

int main() {
	scanf("%s", str);
	int count = 0;
	int sum = 0;
	for (int i = 0; i< strlen(str); i++) {
		if (i == strlen(str) - 1) {
			printf("%d\n", sum);
		}
		else if (str[i] == '('&&str[i + 1] == ')') {
			if (count != 0) {
				sum += count;
			}
		}
		else if (str[i] == ')'&&str[i + 1] == ')') {
			count--; sum++;
		}
		else if (str[i] == ')');
		else count++;
	}
	return 0;
}
