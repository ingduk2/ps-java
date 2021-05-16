package ps.baekjoon.backtracking;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N과 M (5)
 * https://www.acmicpc.net/problem/15654
 *
 * 순열 사전순
 *
 * sysout 시간초과.
 */
public class Q15654 {

    private static StringBuilder sb = new StringBuilder();

    private static void dfs(int[] arr, int[] out, boolean[] visited, int m, int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(out[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                out[depth] = arr[i];
                dfs(arr, out, visited, m, depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Input in = new Input();

        int n = in.nextInt();
        int m = in.nextInt();

        int[] arr = new int[n];
        int[] out = new int[m];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        Arrays.sort(arr);
        dfs(arr, out, visited, m, 0);
        System.out.println(sb);
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
