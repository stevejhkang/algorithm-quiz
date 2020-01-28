import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class Solution {
	int cnt=0;
	Map<Integer,Boolean> mp= new HashMap<>();
    public int solution(String numbers) {
        int answer = 0;
        int visit[] = new int [numbers.length()];
        String str="";
        for(int i=1;i<=numbers.length();i++) { //길이 1부터 n까지 permutation시켜준다.
        	permutation(numbers,str,visit,0,i);
        }
        answer=cnt;
        return answer;
    }
	private void permutation(String numbers, String str,int visit[], int n, int r) {
		if(n==r) { //n==r일 경우  소수인지 체크한다.
//			System.out.println("n==r");
			
			int a= Integer.parseInt(str);
			//System.out.println(a);
			if(mp.containsKey(a)) {
				return;
			}
			if (a==0||a==1) {
				return;
			}
			for(int i=2;i<=Math.sqrt(a);i++) {
				if(a%i==0) {
					return;
				}
			}
//			System.out.println(a);
			mp.put(a,true);
			cnt++;
			return;
		}
		for(int i=0;i<numbers.length();i++) {
			if(visit[i]==0) {
				//System.out.println(numbers.charAt(i));
				str+=Character.toString(numbers.charAt(i));
				visit[i]=1;
				permutation(numbers, str, visit,n+1,r);
				visit[i]=0;
				str=str.substring(0,str.length()-1);
			}
		}
	}
}
