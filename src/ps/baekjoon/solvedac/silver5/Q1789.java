package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 수들의 합
 * https://www.acmicpc.net/problem/1789
 *
 * 숫자가 많아야하므로
 * 1부터 쭉 더해가다가 S를 초과한 경우 cnt -1
 *
 * 4294967295 int로 받으면 numberformatException
 */
public class Q1789 {

    public static void main(String[] args) {
        Input in = new Input();
        long s = in.nextLong();

        long sum = 0;
        int cnt = 0;
        for (int i = 1; i <= s; i++) {
            sum += i;
            cnt++;
            if (sum > s) {
                cnt--;
                break;
            }
        }

        System.out.println(cnt);
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
