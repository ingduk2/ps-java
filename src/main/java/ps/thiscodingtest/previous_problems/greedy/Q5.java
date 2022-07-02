package ps.thiscodingtest.previous_problems.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 볼링공 고르기
 */
public class Q5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        ////my////
        int[] array = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int arrLen = array.length;
        int result = 0;
        for (int i = 0; i < arrLen; i++) {
            for (int j = i; j < arrLen; j++) {
                if (array[i] != array[j]) {
                    result ++;
                }
            }
        }
        System.out.println(result);
        ////my////

        ////answer////
        int[] weight = new int[11];
        for (int x : array) {
            weight[x] += 1;
        }
        result = 0;
        for (int i = 1; i < m + 1; i++) {
            n -= weight[i];
            result += weight[i] * n;
        }
        System.out.println(result);
        ////answer////

    }
}
