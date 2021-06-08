package ps.baekjoon.solvedac.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 검증수
 * https://www.acmicpc.net/problem/2475
 */
public class Q2475 {

    public static void main(String[] args) {
        Input in = new Input();
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            int n = in.nextInt();
            sum += Math.pow(n, 2);
        }

        System.out.println(sum % 10);
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
