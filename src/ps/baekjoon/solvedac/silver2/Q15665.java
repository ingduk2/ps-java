package ps.baekjoon.solvedac.silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N과 M (11)
 * https://www.acmicpc.net/problem/15665
 *
 * N개의 자연수 중에서 M개를 고른 수열
 * 같은 수를 여러 번 골라도 된다.
 *
 * 중복 순열인듯
 * 4 2
 * 9 7 9 1
 * 1 7
 * 7 1 이 나오므로.
 *
 * 같은 수가 있으므로 걸러주어야함
 *
 */
public class Q15665 {

    private static StringBuilder sb = new StringBuilder();

    private static void dfs(int[] arr, int[] out, int start, int m, int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(out[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int before = 0;
        for (int i = 0; i < arr.length; i++) {
            if (before != arr[i]) {
                before = arr[i];
                out[depth] = arr[i];
                dfs(arr, out, i, m, depth + 1);
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

        int[] out = new int[n];
        Arrays.sort(arr);
        dfs(arr,out, 0, m, 0);
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
