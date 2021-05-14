package ps.baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N과 M (4)
 * https://www.acmicpc.net/problem/15652
 *
 * 중복 조합
 * dfs안에서 dfs인자로 start 를 주면 2 1 같은 내림이 나오므로
 * i를 줘서 i부터 시작해야함.
 */
public class Q15652 {

    private static void dfs(int[] arr, int[] result, int start, int m, int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < arr.length; i++) {
            result[depth] = arr[i];
            dfs(arr, result, i, m, depth + 1);
        }

    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int m = in.nextInt();

        int[] arr = new int[n];
        int[] result = new int[n];

        for (int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }

        dfs(arr, result, 0, m, 0);
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
