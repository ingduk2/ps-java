package ps.baekjoon.testprepare.part1;

import java.util.*;

/**
 * 일곱 난쟁이
 * https://www.acmicpc.net/problem/2309
 */
public class Q2309 {
    private static List<Integer> answer;
    private static boolean isEnd;

    private static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if (isEnd) return;
        if (r == 0) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    list.add(arr[i]);
                }
            }

            int sum = 0;
            for (Integer i : list) {
                sum += i;
            }

            if (sum == 100) {
                Collections.sort(list);
                answer = list;
                isEnd = true;
            }

            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[9];
        for (int i = 0; i < 9; i++) {
            arr[i] = sc.nextInt();
        }

        boolean[] visited = new boolean[9];
        combination(arr, visited, 0, 9, 7);

        for (Integer a : answer) {
            System.out.println(a);
        }
    }
}
