#include <string>
#include <vector>
#include<queue>
#include<string>
using namespace std;
/////////////BFS/////////////////////////////
int visited[50];

bool is_right(string s1, string s2){
    int s_size = s1.size();
    int count = 0;
    for(int i=0;i<s_size;i++){
        if(s1[i]!=s2[i]){
            count++;
        }
    }
    return (count==1)?true:false;
}


int solution(string begin, string target, vector<string> words) {
    int answer = 0;
    queue<pair<string,int>> q;
    q.push(make_pair(begin,0)); //시작을 넣어주고
    
    while(!q.empty()){
        string now=q.front().first; int cng=q.front().second; q.pop();
        for(int j=0;j<words.size();j++){
             //ooo 
            if(now==target){ //현재랑 target이랑 같으면 answer값 넣어주고 끝낸다.
                answer=cng;
                break;
            }
            if(!visited[j]){//방문 안 했으면       
                if(is_right(words[j],now)){
                    visited[j]=1;
                    q.push(make_pair(words[j],cng+1));  
                }
            }
        }
    }
    return answer;
}
