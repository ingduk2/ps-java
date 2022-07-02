package ps.baekjoon.solvedac.bronze1;

import java.util.Scanner;

/**
 * 유진수
 * https://www.acmicpc.net/problem/1356
 *
 * 12345
 * 1 2345, 12 345, 123 45, 1234 5
 *
 * substring 도 가능.
 */
public class Q1356 {

    private static String getNumber(int start, int end, String num) {
        StringBuilder sb = new StringBuilder();
        for (int j = start; j < end; j++) {
            sb.append(num.charAt(j));
        }

        return sb.toString();
    }

    private static int getMultiply(String num) {
        int mul = 1;
        for (int i = 0; i < num.length(); i++) {
            mul *= Character.getNumericValue(num.charAt(i));
        }
        return mul;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num = sc.next();

        int len = num.length();
        for (int i = 0; i < len - 1; i++) {

            int mul1 = getMultiply(getNumber(0, i + 1, num));
            int mul2 = getMultiply(getNumber(i + 1, len, num));

            if (mul1 == mul2) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }


}
