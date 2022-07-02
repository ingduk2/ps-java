package ps.baekjoon.solvedac.bronze2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 그릇
 * https://www.acmicpc.net/problem/7567
 *
 * (( +5
 * () +10
 * )( +10
 * )) +5
 *
 * 같은 모양일 경우 +5
 * 다른 모양일 경우 +10
 *
 */
public class Q7567 {

    public static void main(String[] args) {
        Input in = new Input();
        char[] chars = in.next().toCharArray();

        int height = 10;
        char prev = chars[0];
        for (int i = 1; i < chars.length; i++) {
            char current = chars[i];
            if (prev == current) height += 5;
            else height += 10;
            prev = current;
        }

        System.out.println(height);
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
