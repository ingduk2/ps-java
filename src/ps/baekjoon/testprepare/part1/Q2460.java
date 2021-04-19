package ps.baekjoon.testprepare.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 지능형 기차2
 * https://www.acmicpc.net/problem/2460
 */
public class Q2460 {
    public static void main(String[] args) {
        Input input = new Input();
        int max = 0;
        int inCount = 0;
        for (int i = 0; i < 10; i++) {
            int out = input.nextInt();
            int in = input.nextInt();
            inCount += in;
            inCount -= out;
            max = Math.max(max, inCount);
        }
        System.out.println(max);
    }

    private static class Input{
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
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
