package ps.baekjoon.solvedac.silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 토너먼트
 * https://www.acmicpc.net/problem/1057
 *
 * 1 2 3 4 5 6 7 8 9 10 11 12
 *  1 2 3        4 5      6
 *   1           2 3
 *   1            2
 *
 *   8 -> 4 -> 2 -> 1
 *   9 -> 5 -> 3 -> 2
 *
 *   n = n / 2 + n % 2;
 * or n = n - n / 2;
 *   n 값이 같기 전까지
 *
 *
 * 수학
 */
public class Q1057 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int kim = in.nextInt();
        int lim = in.nextInt();

        int round = 0;
        while (kim != lim) {
            kim = kim / 2 + kim % 2;
            lim = lim / 2 + lim % 2;
            round ++;
        }

        System.out.println(round);
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
