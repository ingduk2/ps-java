package ps.baekjoon.solvedac.silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N과 M (9)
 * https://www.acmicpc.net/problem/15663
 *
 * 순열 사전순
 * 중복되는 수열을 제거해야하므로
 * 4 2
 * 9 7 9 1 (1 7 9 9)
 *
 * 1 7
 * 1 9
 * -> 1 9 후에 1 (사이에서 이전 9 와 비교) 9
 * -> 9 가 한번 더나오면 안됨.
 * 1 9
 */
public class Q15663 {

    private static StringBuilder sb = new StringBuilder();

    private static void dfs(int[] arr, int[] out, boolean[] visited, int m, int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(out[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int before = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i] && before != arr[i]) {
                visited[i] = true;
                before = arr[i];
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
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        Arrays.sort(arr);
        int[] out = new int[n];
        boolean[] visited = new boolean[n];
        dfs(arr, out, visited, m, 0);
        System.out.println(sb);
    }


    private static class Input {
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
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

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
