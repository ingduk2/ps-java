package ps.baekjoon.step.step5;

import java.util.Scanner;

/**
 * 최댓값
 * https://www.acmicpc.net/problem/2562
 */
public class Q2562 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int max = 0;
        int idx = 0;
        for (int i = 0; i < 9; i++) {
            int input = sc.nextInt();
            if (max < input){
                max = input;
                idx = i;
            }
        }
        System.out.println(max);
        System.out.println(idx + 1);
    }
}
