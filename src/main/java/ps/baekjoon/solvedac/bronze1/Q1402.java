package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아무래도이문제는A번난이도인것같다
 * https://www.acmicpc.net/problem/1402
 *
 * 하나씩 찾아보려했는디
 * 모든 숫자가 다 가능..
 *
 * A = 0
 * 0*1*1*1 로 양수
 * 0*-1*-1 로 음수
 *
 * A > 0
 * A = -A * -1 * 1 * 1 * 1
 * 8 2 -> (-8 * -1) * 1 * 1 * 1 * 1 * 1 * 1 * 1 * 1 * 1 * 1 = 8
 *        -8 -1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 = 2
 *
 * A < 0
 * 위와 비슷하게 가능
 *
 */
public class Q1402 {

    public static void main(String[] args) {
        Input in = new Input();
        int t = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();

        for (int i = 0; i < t; i++) {
            System.out.println("yes");
        }

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
