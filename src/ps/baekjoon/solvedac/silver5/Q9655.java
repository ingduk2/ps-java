package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 돌 게임
 * https://www.acmicpc.net/problem/9655
 *
 * 돌이 1, 3개 홀수개.
 * n
 * 1 sk
 * 2 cy
 * 3 sk
 * 4 cy
 *
 * 홀수이면 SK, 짝수이면 CY
 */
public class Q9655 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        if (n % 2 == 0) {
            System.out.println("CY");
        } else {
            System.out.println("SK");
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
