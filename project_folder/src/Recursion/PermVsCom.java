package Recursion;

public class PermVsCom {
	static char [] src = {'A','B','C','D'};
	static boolean[] visit= new boolean[4];
	public static void main(String[] args) {
		com(0,"",0);
//		perm(0, "");
	}
	static void com(int k,String s,int idx) {
		if(k==2) {
			System.out.println(s);
			return;
		}
		for(int i=idx;i<4;i++) {
			if(!visit[i]) {
				visit[i]=true;
				com(k+1, s+src[i], i);
				visit[i]=false;
			}
			
		}
	}
	static void perm(int k,String s) {
		if(k==2) {
			System.out.println(s);
			return;
		}
		for(int i=0;i<4;i++) {
			if(!visit[i]) {
				visit[i]=true;
				perm(k+1, s+src[i]);
				visit[i]=false;
			}
			
		}
	}
}
