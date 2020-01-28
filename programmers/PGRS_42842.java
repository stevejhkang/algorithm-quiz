class Solution {
    public int[] solution(int brown, int red) {
        int[] answer = new int[2];
        for(int i=1;i<=Math.sqrt(red);i++) {
        	int red_hori=red%i;
        	//System.out.println(red_hori);
        	if(red_hori==0) {
        		int horizon=2*(red/i);
        		int verti=2*i;
        		System.out.println(red/i);
        		System.out.println(i);
        		if(horizon+verti+4==brown) {
        			answer[0]=red/i+2;
        			answer[1]=i+2;
        			break;
        		}
        	}
        }
        return answer;
    }
}
