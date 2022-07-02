package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 약수
 * https://www.acmicpc.net/problem/1037
 *
 * 최대 * 최소값
 * 8, 1 2 4 8 -> 2 4
 * 20, 1 2 4 5 10 20 -> 2 4 5 10
 * 1과 N은 들어오지 않는다.
 */
public class Q1037 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        System.out.println(min * max);
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
