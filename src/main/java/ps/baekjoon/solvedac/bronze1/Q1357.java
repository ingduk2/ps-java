package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 뒤집힌 덧셈
 * https://www.acmicpc.net/problem/1357
 */
public class Q1357 {

    private static int rev(int num) {
        StringBuilder sb = new StringBuilder();
        StringBuilder reverse = sb.append(num).reverse();

        return Integer.parseInt(reverse.toString());
    }

    public static void main(String[] args) {
        Input in = new Input();
        int x = in.nextInt();
        int y = in.nextInt();

        System.out.println(rev(rev(x) + rev(y)));
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
