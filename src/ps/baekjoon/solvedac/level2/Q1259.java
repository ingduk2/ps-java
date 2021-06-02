package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 팰린드롬수
 * https://www.acmicpc.net/problem/1259
 */
public class Q1259 {

    public static void main(String[] args) {
        Input in = new Input();
        while (true) {
            String num = in.next();
            if (num.equals("0")) break;

            String reverse = new StringBuilder(num).reverse().toString();
            if (num.equals(reverse)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }

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
    }
}
