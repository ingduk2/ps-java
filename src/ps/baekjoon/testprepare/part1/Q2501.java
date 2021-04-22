package ps.baekjoon.testprepare.part1;

import java.util.Scanner;

/**
 * 약수구하기
 * https://www.acmicpc.net/problem/2501
 */
public class Q2501 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int cnt = 0;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                cnt++;
            }

            if (cnt == k) {
                System.out.println(i);
                return;
            }
        }

        if (cnt + 1 == k) {
            System.out.println(n);
        } else {
            System.out.println(0);
        }
    }
}
