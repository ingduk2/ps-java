package ps.baekjoon.solvedac.silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 01타일
 * https://www.acmicpc.net/problem/1904
 *
 * n = 1
 * 1
 *
 * n = 2
 * 00 11
 *
 * n = 3
 * 001 100 111
 *
 * n = 4
 * 0000 1100 0011 1001 1111
 *
 * n = 5
 * 00100 10000 11111 00001 11001 00111 10011 11111
 *
 * n - 1에서 1 붙이고, n - 2 에서 00 붙이기
 * d[n] = d[n - 1] + d[n - 2]
 *
 */
public class Q1904 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        int[] d = new int[n + 2];
        d[1] = 1;
        d[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            d[i] = (d[i - 1] + d[i - 2]) % 15746;
        }

        System.out.println(d[n]);
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
