package ps.baekjoon.solvedac.silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N과 M (6)
 * https://www.acmicpc.net/problem/15655
 *
 * 조합
 */
public class Q15655 {

    private static StringBuilder sb = new StringBuilder();

    private static void dfs(int[] arr, int[] out, int start, int m, int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(out[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < arr.length; i++) {
            out[depth] = arr[i];
            dfs(arr, out, i + 1, m, depth + 1);
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
        dfs(arr, out, 0, m, 0);
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
