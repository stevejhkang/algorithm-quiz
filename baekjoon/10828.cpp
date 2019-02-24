#include <cstdio>
#include<string.h>


int arr[10001];
int current = -1;

void push() {
	int num;
	scanf("%d",&num); 
	current++;
	arr[current] = num;
}
void pop() {
	if (current <= -1) {printf("-1\n"); return; }
	printf("%d\n",arr[current]);
	current--;
	return;
}
void empty() {
	if (current <= -1){ printf("1\n"); return;}
	else printf("0\n");
}
void top() {
	if(current<=-1){ printf("-1\n"); return;}
    printf("%d\n",arr[current]);
    
}
void size() {
	printf("%d\n",current+1);
}
int main() {
	int n;
    char order[6];
	scanf("%d",&n); 
	for (int i = 0; i<n; i++) {
		scanf("%s",order);
		if (strcmp(order,"push")==0) push();
		else if (strcmp(order,"pop")==0) pop();
		else if (strcmp(order,"empty")==0) empty();
		else if (strcmp(order,"top")==0) top();
		else if (strcmp(order,"size")==0) size();
	}
	return 0;
}
