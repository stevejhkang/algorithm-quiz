package SWEA;

import java.util.HashMap;
import java.util.Scanner;

public class SWEA_D4_5604_구간합_수열 {

    static int T;
    static long A, B, S;
    static HashMap<Long, Long> m = new HashMap<Long, Long>();

    /**
     * 9 99 999 9999 99999 이런식으로 값이 들어올 때 취할 수 있는 자리수의 합들을 미리 구해놓기 - 정해져있으니까..
     * 중간에 정거장? 처럼 사용할 수 있는 거점들을 미리 구해 놓음 - 속도 향상 목적
     */
    static void setup() {
        // 0<=자리수<=15승이니까 1~16까지 가면서 구해놓음
        for (long i = 1; i < 17; i++) {
            long v = pow10(i);
            m.put((v - 1), getFull(v - 1));
        }
    }

    /**
     * 9, 99, 999 등의 숫자가 왔을 때 가지는 각 자리수의 숫자의 합은?
     * F( 9) = 0+1+2+3+4+5+6+7+8+9 = 45
     * F(19) = F(9)+10+11+12+13+14+15+16+17+18+19 = F(9)+1*10+F(9) =1*10+2*F(9)
     * F(29) = F(19)+20+21+22+23+24+25+26+27+28+29 = F(19)+2*10+F(9)= 1*10+2*F(9)+2*10+F(9) =(1+2)*10+3F(9)
     * F(39) = F(29)+30+31+32+33+34+35+36+37+38+39 = F(29)+3*10+F(9)= (1+2)*10+3F(9)+3*10+F(9) =(1+2+3)*10+4F(9)
     * ...
     * F(9)=1*F(9)=45
     * F(99)=20*F(9)
     * F(999)=300*F(9)
     * F(9999)=4000*F(9)
     * 
     * @param n
     * @return
     */
    public static long getFull(long n) {
        // getDigit 함수가 자리수 -1을 반환해서 +1 해줌. 9--> 1로, 99-> 2로..
        long len = getDigit(n) + 1L;
        // f(9)=이게 45L, f(99)=20*f(9) 300*f(9) 4000*f(9) - 왜 이렇게 곱하면 되는지는 첨부 그림 참조
        // 9인 경우 0+1+2+3+4+5+6+7+8+9=45, 그럼 99는?
        return ((45L) * (len) * (1 + n)) / (10);

    }

    public static void main(String[] args) {
        // 큰 녀석들 미리 구해 놓기
        setup();

        Scanner scann = new Scanner(System.in);
        scann = new Scanner(src);
        T = scann.nextInt();

        for (int i = 1; i <= T; i++) {
            A = scann.nextLong();
            B = scann.nextLong();
            S = getDigitSum(B) - getDigitSum(A - 1);
            System.out.println("#" + i + " " + S);
        }
    }

    // 어떤 단위까지의 값 구하기 ( ex)
    public static long getDigitSum(long n) {
        // 이미 구해놓은 값이면 그 값 사용하기
        if (m.containsKey(n)) {
            return m.get(n);
        }
        // 10보다 작다면 간단히 더하기
        else if (n <= 9) {
            return sumTo(n);
        }
        // 실제로 구해주기.
        else {
            long v = pow10(getDigit(n));
            m.put(n, getDigitSum(n - 1 - n % v) + getDigitSumPart(n));
            return m.get(n);
        }
    }



    // 쪼가리 값 구하기.
    public static long getDigitSumPart(long n) {
        // 10보다 작다면 간단히 더히기
        if (n <= 9) {
            return sumTo(n);
        } else {
            long v = pow10(getDigit(n));
            return (n / v) * (n % v + 1L) + getDigitSum(n % v);
        }
    }

    public static long sumTo(long n) {
        return n * (n + 1) / 2;
    }

    /**
     * 숫자의 자리수를 리턴해주는 함수
     * 주의할 점은 여기 저기의 용도에 따라 자리수 -1을 리턴해주고 있다. 즉 9 -> 0, 10 -> 1 이런 식
     * 
     * @param n
     * @return
     */
    public static long getDigit(long n) {
        // return 0L + (n + "").length() - 1;
        return (n + "").length() - 1;
    }

    /**
     * 10^n을 long으로 처리하기 위한 함수
     * 
     * @param n
     * @return
     */
    public static long pow10(long n) {
        return (long) Math.pow(10, n);
    }

    private static String src = "3\r\n" +
                                "0 10\r\n" +
                                "8 12\r\n" +
                                "33 133";



}
