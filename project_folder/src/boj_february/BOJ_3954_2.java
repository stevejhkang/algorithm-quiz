package boj_february;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
 
 
public class BOJ_3954_2 {
    private static int[] arr;
	private static char[] codes;
	private static char[] input;
	private static int arrLen;
	private static int codeLen;
	private static int inputLen;
	private static int ptr;
	private static int code_i;
	private static int input_i;
	static final int div = 1 << 8;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        
        for(int tc = 0 ; tc < t ; tc++) {
            st = new StringTokenizer(br.readLine());
            arrLen = Integer.parseInt(st.nextToken());
            codeLen = Integer.parseInt(st.nextToken());
            inputLen = Integer.parseInt(st.nextToken());
            arr = new int[arrLen];
            codes = new char[codeLen];
            input = new char[inputLen];
            for(int i = 0 ; i < arrLen ; i++) {
                arr[i] = 0;
            }
            String tmp = br.readLine();
            for(int i = 0 ; i < codeLen ; i++) {
                codes[i] = tmp.charAt(i);
                pairs[i] = -1;
            }
            
            tmp = br.readLine();
            for(int i = 0 ; i < inputLen ; i++) {
                input[i] = tmp.charAt(i);
            }
            
            ptr = 0;
            code_i = 0;
            input_i = 0;
            
            makePair(codes, codeLen);
            
            int cmd;
            int lastLoop = -1;
            for(cmd = 0 ; cmd < 50000000 ; cmd++) {
                if(codes[code_i] == '+') {
                    arr[ptr]++;
                    arr[ptr] = arr[ptr] % div;
                    code_i++;
                }else if(codes[code_i] == '-') {
                    arr[ptr]--;
                    if(arr[ptr] < 0)
                        arr[ptr] = div - 1;
                    code_i++;
                }else if(codes[code_i] == '<') {
                    ptr--;
                    if(ptr < 0)
                        ptr = arrLen - 1;
                    code_i++;
                }else if(codes[code_i] == '>') {
                    ptr++;
                    if(ptr == arrLen)
                        ptr = 0;
                    code_i++;
                }else if(codes[code_i] == '[') {
                    if(arr[ptr] == 0) {
                        code_i = pairs[code_i];
                    }else {
                        code_i++;
                    }
                }else if(codes[code_i] == ']') {
                    if(arr[ptr] != 0) {
                        if(lastLoop < code_i)
                            lastLoop = code_i;
                        code_i = pairs[code_i];
                    }else {
                        code_i++;
                    }
                }else if(codes[code_i] == '.') {
                    code_i++;
                }else {
                    if(input_i == inputLen) {
                        arr[ptr] = 255;
                    }else {
                        arr[ptr] =  input[input_i++];
                    }
                    code_i++;
                }
                
                if(code_i == codeLen)
                    break;
            }
            if(code_i == codeLen)
                System.out.println("Terminates");
            else
                System.out.println("Loops " +pairs[lastLoop]+" "+lastLoop);
        }
    }
    static int[] pairs = new int[4097];
    static void makePair(char[] codes, int codeLen ) {
        Stack<Integer> stack = new Stack<Integer>();
        int closePair ;
        
        for(int i = 0 ; i < codeLen ; i++) {
            if(codes[i] == '[') {
                stack.push(i);
            }
            if(codes[i] == ']') {
                closePair = stack.pop();
                pairs[i] = closePair;
                pairs[closePair] = i;
            }
        }
    }
}