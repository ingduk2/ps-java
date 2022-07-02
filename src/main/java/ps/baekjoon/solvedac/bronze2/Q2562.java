package ps.baekjoon.solvedac.bronze2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 최댓값
 * https://www.acmicpc.net/problem/2562
 */
public class Q2562 {

    private static int size = 9;

    public static void main(String[] args) {
        Input in = new Input();

        int idx = 0;
        int max = 0;
        for (int i = 0; i < size; i++) {
            int number = in.nextInt();
            if (max < number) {
                idx = i + 1;
                max = number;
            }
        }

        System.out.println(max);
        System.out.println(idx);
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
