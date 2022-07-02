package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 분해합
 * https://www.acmicpc.net/problem/2231
 */
public class Q2231 {

    private static int sum(int i) {
        int sum = i;
        while (i != 0) {
            sum += i % 10;
            i /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        for (int i = 1; i <= 1000000; i++) {
            if (n == sum(i)) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(0);
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
    }
}
