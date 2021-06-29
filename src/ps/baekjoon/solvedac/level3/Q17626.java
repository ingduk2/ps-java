package ps.baekjoon.solvedac.level3;

import java.util.Scanner;

/**
 * Four Squares
 * https://www.acmicpc.net/problem/17626
 *
 * dp
 * 1 -> 1개 , 1
 * 2 -> 2개, 1 + 1
 * 3 -> 3개, 1 + 1 + 1
 * 4 -> 1개, 2
 * 5 -> 2개, 2 + 1
 * 6 -> 3개, 2 + 1 + 1
 * 7 -> 4개, 2 + 1 + 1 + 1
 * 8 -> 2개, 2 + 2
 * 9 -> 1개, 3
 *
 * d[i] = min(d[i - j^2]) + 1
 * i 보다 작거나 같은 제곱수 j^2 중 최소값 + 1
 * d[0] = 0, d[1] = 1
 *
 *
 */
public class Q17626 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] d = new int[500001];
        d[0] = 0;
        d[1] = 1;

        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(d[i - j * j], min);
            }
            d[i] = min + 1;
        }

        System.out.println(d[n]);
    }

}
