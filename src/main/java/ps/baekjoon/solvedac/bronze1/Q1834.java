package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 나머지와 몫이 같은 수
 * https://www.acmicpc.net/problem/1834
 *
 * n - 1번
 * 3 -> 4, 8 (3*1 + 1, 3*2 + 2)
 * 4 -> 5, 10, 15(4*1 + 1, 4*2 + 2, 4*3 + 3
 * 5 -> 6, 12, 18, 24(5*1 + 1, 5*2 + 2, 5*3 + 3, 5*4 + 4)
 *
 * 2000000 * 2000000 도 가능하기때문에 long으로.
 */
public class Q1834 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        long sum = 0;

        for (int i = 1; i < n; i++) {
            sum += n * (long)i + i;
        }

        System.out.println(sum);
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
