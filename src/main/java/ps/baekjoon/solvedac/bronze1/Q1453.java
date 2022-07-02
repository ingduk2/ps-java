package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 피시방 알바
 * https://www.acmicpc.net/problem/1453
 */
public class Q1453 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        boolean[] pc = new boolean[101];
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            if (!pc[num]) {
                pc[num] = true;
            } else {
                ret++;
            }
        }

        System.out.println(ret);
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
