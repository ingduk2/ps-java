package ps.baekjoon.testprepare.part1;

import java.util.Scanner;

/**
 * 이진수
 * https://www.acmicpc.net/problem/3460
 */
public class Q3460 {

    private static String getBinaryReverseStr(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int rest = n % 2;
            n /= 2;
//            sb.insert(0, rest);
            sb.append(rest);
        }
        return sb.toString();
    }

    private static void print(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            String binaryReverseStr = getBinaryReverseStr(n);
            print(binaryReverseStr);
        }
    }
}
