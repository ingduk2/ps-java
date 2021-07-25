package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 다리 놓기
 * https://www.acmicpc.net/problem/1010
 *
 * 조합 계산
 */
public class Q1010 {

    private static int[][] d = new int[30][30];

    private static int combination(int n, int r) {
        if(d[n][r] > 0) return d[n][r];

        if (n == r || r == 0) return d[n][r] = 1;

        d[n][r] = combination(n - 1, r - 1) + combination(n - 1, r);
        return d[n][r];
    }

    public static void main(String[] args) {

        StringBuilder result = new StringBuilder();
        Input in = new Input();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();

            result.append(combination(m, n)).append("\n");
        }

        System.out.println(result);
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

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
