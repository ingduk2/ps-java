package ps.baekjoon.testprepare.part1;

import java.util.Scanner;

/**
 * 피보나치 수 5
 * https://www.acmicpc.net/problem/10870
 */
public class Q10870 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] d = new int[n + 2];
        d[0] = 0;
        d[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            d[i] = d[i - 1] + d[i - 2];
        }

        System.out.println(d[n]);
    }
}
