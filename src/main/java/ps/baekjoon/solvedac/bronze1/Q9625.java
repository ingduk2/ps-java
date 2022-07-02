package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BABBA
 * https://www.acmicpc.net/problem/9625
 * A 1 0
 * B 0 1
 * BA 1 1
 * BAB 1 2
 * BABBA 2 3
 * BA B BA BA B 3 5
 * BA B BA BA B BA B BA 5 8
 * BA B BA BA B BA B BA BA B BA BA B 8 13
 *
 * A -> B, B-> BA
 * a 개수, b 개수
 * d[n] = d[n-1] + d[n-2]
 *
 *
 */
public class Q9625 {

    public static void main(String[] args) {
        Input in = new Input();
        int k = in.nextInt();
        int[] a = new int[46];
        int[] b = new int[46];
        a[0] = 1;
        b[0] = 0;
        a[1] = 0;
        b[1] = 1;


        for (int i = 2; i < 46; i++) {
            a[i] = a[i - 1] + a[i - 2];
            b[i] = b[i - 1] + b[i - 2];
        }

        System.out.println(a[k] + " " + b[k]);
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

        public long nextLong() { return Long.parseLong(next()); }

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
