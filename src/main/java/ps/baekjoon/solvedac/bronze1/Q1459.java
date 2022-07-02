package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 걷기
 * https://www.acmicpc.net/problem/1459
 *
 * 1. 한칸씩 이동. (x + y) * w
 * 2. 대각선으로만 이동.
 * 홀수 : 대각선 이동 후 한칸, (Max(x, y) - 1 * s + w) (대각선으로 갈만큼 간 후 1칸 이동하면 최소)
 * 짝수 : 대각선으로만 가능. (4, 2 의 경우 4번에 가능. 아래위가 가능하기 때문에 Max(x, y) * s)
 * 3. 섞어서 이동.
 * 대각선으로 최소길이만큼 올라간 후 한칸 이동
 * (Math.min(x, y) * s + Math.abs(x-y) * w)
 *
 * 설명 잘되어있음.
 * https://buzz-program.tistory.com/entry/BOJ1459%EA%B1%B7%EA%B8%B0
 */
public class Q1459 {

    public static void main(String[] args) {
        Input in = new Input();
        long x = in.nextLong();
        long y = in.nextLong();
        long w = in.nextLong();
        long s = in.nextLong();

        long cost1, cost2, cost3;

        cost1 = (x + y) * w;

        if ((x + y) % 2 == 0) {
            cost2 = Math.max(x, y) * s;
        } else {
            cost2 = (Math.max(x, y) - 1) * s + w;
        }

        cost3 = Math.min(x, y) * s + Math.abs(x - y) * w;

        System.out.println(Math.min(Math.min(cost1, cost2), cost3));
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

        public long nextLong() {return Long.parseLong(next()); }

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
