package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

/**
 * @author steve.jh.kang@gmail.com
 * @time 2020. 3. 13. 오후 1:13:42
 * @category 
* @problem_description 알파벳, 세종류 괄호, 온점, 개행(\n)
* 소괄호 중괄호 대괄호는 서로 짝이 잘 맞도록 써야한다. 
* 이 언어는 다른 언어들과는 다르게 공백을 사용하지 않고 온점을 이용해 들여쓰기를 표현한다. 
* 들여쓰기가 잘 된 코드는 각 괄호가 등장한 횟수에 따라 들여쓰기하는 정도가 달라지게 되는데
* 세정수 RCS<=20을 만족하는 세정수에 대해 이전 줄에 소괄호가 짝이 맞지 않는 개수만큼 R번씩, 중괄호가 짝이 
* 맞지않는 개수만큼 C번씩, 대괄호가 짝이 맞지 않는 개수만큼 S번씩 들여쓰기 했다면 들여쓰기가 잘 되었다고한다.
* @solving_description 
* 들여쓰기 해야되는 수는  R(a – b) + C(c – d) + S(e – f) 소 중 대
* a는 (개수, b는 )의 개수, c -> { d ->} e->[ f->]
* p는 마스터한사람의 코드 줄 수 q는 당신의 코드 줄 수
* 마스터 한사람의 코드를 분석하여 RCS를 알아내고 그걸 가지고 내 코드를 파악하여 줄마다 몇번 들여쓰기 해야되는지
* 파악해야한다.
* 만약 몇번 들여쓰기 하는 지 정확하지 않아서 들여쓰기가 유일하게 결정되지 않는다면 -1 출력
*/

public class SWEA_3378 {
    public static void main(String[] args) throws IOException {
       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int p =Integer.parseInt(stringTokenizer.nextToken()); 
            int q =Integer.parseInt(stringTokenizer.nextToken()); 
            String[] master = new String[p];
            String[] me = new String[q];
            for(int i = 0; i < p; i++)
                master[i] =bufferedReader.readLine();
            for(int i = 0; i < q; i++)
                me[i] = bufferedReader.readLine();
             
            int[] result = new int[q];
            Arrays.fill(result, -2);
            for(int r = 1; r <= 20; r++) {
                for(int c = 1; c <= 20; c++) {
                    for(int s = 1; s <= 20; s++) {
                        if(can(master , r , c ,s )) {
                            calIndent(me, r, c, s, result);
                        }
                    }
                }
            }
            System.out.print("#" + tc + " ");
            for(int indent : result) {
                System.out.print(indent + " ");
            }
            System.out.println();
        }
    }
    static void calIndent(String[] me, int r, int c, int s, int[] result) {
        int rCnt = 0, cCnt = 0, sCnt = 0;
        for(int i = 0; i < me.length; i++) {
            if(result[i] == -2)
                result[i] = r * rCnt + c * cCnt + s * sCnt;
            else {
                if( result[i] != r*rCnt + c * cCnt + s * sCnt){
                    result[i] = -1;
                }
            }
            for(char ch : me[i].toCharArray() ) {
            	if(ch=='(') rCnt++;
            	else if(ch==')') rCnt--;
            	else if(ch=='{') cCnt++;
            	else if(ch=='}') cCnt--;
            	else if(ch=='[') sCnt++;
            	else if(ch==']') sCnt--;
            }
        }
    }
     
    static boolean can(String[] master, int r, int c, int s) {
        int rCnt = 0, cCnt = 0, sCnt = 0;
        for(int i = 0; i < master.length; i++) {
            int cnt = 0;
            for(char ch : master[i].toCharArray()) {
                if( ch == '.' )
                    cnt++;
                else
                    break;
            }
            
            if( cnt != r * rCnt + c * cCnt + s * sCnt )
                return false;
            
            for(char ch : master[i].toCharArray() ) {
            	if(ch=='(') rCnt++;
            	else if(ch==')') rCnt--;
            	else if(ch=='{') cCnt++;
            	else if(ch=='}') cCnt--;
            	else if(ch=='[') sCnt++;
            	else if(ch==']') sCnt--;
                
            }
        }
        return true;
    }
}

