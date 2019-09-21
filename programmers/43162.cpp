#include <string>
#include <vector>
#include<stack>
using namespace std;

int visited[200];//전역변수 처리 안하면 초기화 해줘야 된다!!!!!!!!

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;

    stack<int> stk;
    for(int i=0;i<n;i++){
        if(visited[i]==0){ 
            //만약 방문하지 않았으면 새로운 네트워크이므로
            //네트워크 개수 갱신 후, 방문 표시 후, 스택에 넣어준다.
            answer++;
            visited[i]=1;
            stk.push(i);
        }
        else{//방문했으면 연결 된 것이므로 그냥 넘어간다.
          continue;  
        } 
        while(!stk.empty()){
            int now=stk.top(); stk.pop();
            for(int j=0;j<n;j++){ //스택 맨 위에 있는 것과 연결된 모든 것을 찾아준다.
                //현재 now와 j가 만나야 같은 네트워크
                if(!visited[j]&&now!=j&&computers[now][j]){
                    //같은 네트워크는 방문 처리하고 j와 같은 네트워크를 찾기위해
                    //stack에 넣어준다.
                    visited[j]=1;
                    stk.push(j); 
                }
            }
        }
    }
    return answer;
}
