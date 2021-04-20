package ps.baekjoon.testprepare.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 쉽게 푸는 문제
 * https://www.acmicpc.net/problem/1292
 */
public class Q1292 {

    public static void main(String[] args) {
        Input in = new Input();
        int a = in.nextInt();
        int b = in.nextInt();

        int sum = 0;
        int idx = 1;
        for (int i = 1; i < 1000; i++) {
            for (int j = 0; j < i; j++) {

                if (idx > b) {
                    System.out.println(sum);
                    return;
                }

                if (idx >= a) sum += i;

                idx++;
            }
        }
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
