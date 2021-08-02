package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 캠핑
 * https://www.acmicpc.net/problem/4796
 * l p v
 * p일중 l일동안 사용. v 휴가
 * v 에서 p를 빼준다.
 * 5 8 20
 * 20 -> 8 5
 * v 20 12 4
 *   5  5  4
 *
 * 5 8 17
 * 17 -> 8 5
 * v 17 9 1
 *    5 5 1
 *
 * 5 12 20
 * 20 -> 12 5
 * v 20 8
 *    5 5
 *
 * 마지막 l > v -> v + 남은날이 적음.
 * l <= v -> l + 남은날이 충분함.
 * --> min(l, v)
 *
 */
public class Q4796 {

    public static void main(String[] args) {
        Input in = new Input();
        int caseCnt = 1;
        while (true) {
            int l = in.nextInt();
            int p = in.nextInt();
            int v = in.nextInt();

            if (l + p + v == 0) break;

            int total = 0;
            while (v > 0) {
                if (v - p > 0) {
                    total += l;
                } else {
                    total += Math.min(l, v);
                }
                v = v - p;
            }

            System.out.println("Case " + caseCnt + ": " + total);
            caseCnt++;
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
