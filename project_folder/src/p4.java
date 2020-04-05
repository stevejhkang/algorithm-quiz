import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;

public class p4 {
	public static void main(String[] args) {
		Solution s = new Solution();
		String[][] snapshots = {{"ACCOUNT1", "100"}, {"ACCOUNT2", "150"}}; //계좌이름과 잔액, 숫자와 대소문제가 구분되는 최대 10자리 문자열
		//잔액은 10만보다 작다\
		// 트랜잭션로그 ID, 행위: 세이브, 윗드드로우, 계정, 금액
		//개수는 10만개 
		//트랜잭션 로그는 누락이 없도록 모든 데이터베이스에서 읽어 왔기 때문에 중복이 발생할 수 있고, ID 순서가 랜덤입니다
		//같은 트랜잭션이 중복 적용되지 않도록, ID를 확인하여 한 번만 적용되도록 해야 합니다
		
		//추가 데이터가 생길 수 있다.
		
		//계좌 이름과 잔액을 문자열 배열로 이름이 오른 차순으로 정렬하여 반환 쉼표없이
		
		String[][] transactions = {{"1", "SAVE", "ACCOUNT2", "100"},
				{"2", "WITHDRAW", "ACCOUNT1", "50"}, 
				{"1", "SAVE", "ACCOUNT2", "100"}, 
				{"4", "SAVE", "ACCOUNT3", "500"}, 
				{"3", "WITHDRAW", "ACCOUNT2", "30"}};
		String[][] result = s.solution(snapshots,transactions);
		for(int i=0;i< result.length;i++) {
			System.out.println(result[i][0]+","+result[i][1]);
		}
	}
	static class Solution {
	    public String[][] solution(String[][] snapshots, String[][] transactions) {
	        
	        HashMap<String, Integer> map_snapsets= new HashMap<String, Integer>();
	        for(int i=0;i<snapshots.length;i++) {
	        	map_snapsets.put(snapshots[i][0], Integer.parseInt(snapshots[i][1]));
	        }
	        HashSet<Integer> set_transaction_ids = new HashSet<Integer>();
	        for(int i=0; i<transactions.length;i++) {
	        	if(set_transaction_ids.contains(Integer.parseInt(transactions[i][0]))) {
	        		continue;
	        	}
	        	String order = transactions[i][1];
	        	String account = transactions[i][2];
	        	int money = Integer.parseInt(transactions[i][3]);
	        	set_transaction_ids.add(Integer.parseInt(transactions[i][0]));
	        	if(order.equals("SAVE")) {
	        		if(map_snapsets.containsKey(account)) {
	        			map_snapsets.put(account, map_snapsets.get(account)+money);
	        		}
	        		else {
	        			map_snapsets.put(account, money);
	        		}
	        	}
	        	else { //출금
	        		map_snapsets.put(account, map_snapsets.get(account)-money);
	        	}
	        }//for trans
	        
	        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(map_snapsets);
	        
	        Iterator<String> iteratorKey = sorted_map.keySet( ).iterator( ); 
	        
	        String[] keys= sorted_map.keySet().toArray(new String[0]);
	        Integer[] values = sorted_map.values().toArray(new Integer[0]);

	        String[][] answer = new String [sorted_map.size()][2];
	        for(int i=0;i<sorted_map.size();i++) {
	        	answer[i][0]= keys[i]; answer[i][1] = values[i].toString();
	        }
	        
	        return answer;
	    }
	}
}
