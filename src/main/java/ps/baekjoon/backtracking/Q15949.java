package ps.baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N과 M (1)
 * https://www.acmicpc.net/problem/15649
 *
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 *
 * 사전식으로 나와야함.
 * 그래서 결과베열[depth] = arr[i]
 *
 * 순열
*/
public class Q15949 {

    private static void dfs(int[] arr, int[] perm, boolean[] visited, int m, int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(perm[i] + " ");
            }
            System.out.println();

            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                perm[depth] = arr[i];
                dfs(arr, perm, visited, m, depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Input input = new Input();
        int n = input.nextInt();
        int m = input.nextInt();

        int[] arr = new int[n];
        boolean[] visited = new boolean[n];
        int[] perm = new int[m];

        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        dfs(arr, perm, visited, m, 0);
    }

    private static class Input {
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        private String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
