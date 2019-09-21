#include <string>
#include <vector>
#include <algorithm>
#include<iostream>

using namespace std;

vector<int> solution(vector<int> answers) {
    vector<int> answer;
    vector<int> who(4);
    vector<int> stu1(10001);
    vector<int> stu2(10001);
    vector<int> stu3(10001);
    int stuex1[10]={1,2,3,4,5,1,2,3,4,5};
    int stuex2[8]={2,1,2,3,2,4,2,5};
    int stuex3[10]={3,3,1,1,2,2,4,4,5,5};
    
    for(int i=0; i<10001;i++){
        stu1[i]=stuex1[i%10];
        stu2[i]=stuex2[i%8];
        stu3[i]=stuex3[i%10];
    }
    for(int i=0;i<answers.size();i++){
        if(answers[i]==stu1[i])
            who[1]++;
        if(answers[i]==stu2[i])
            who[2]++;
        if(answers[i]==stu3[i])
            who[3]++;
    }
    int maxindex=0;
    for(int i=1;i<who.size();i++){
        if(maxindex<who[i]){
            answer.clear();
            answer.push_back(i);
            maxindex=who[i];
        }
        else if(maxindex==who[i]){
            answer.push_back(i);
        }    
    }
    for(int i=0;i<answer.size();i++){
        cout<< answer[i]<<",";
    }
    //answer.push_back(maxindex);
    return answer;
}
