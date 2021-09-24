package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 단어 뒤집기
 * https://www.acmicpc.net/problem/9093
 */
public class Q9093 {

    private static void reversePrint(String str) {
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            sb.append(chars[i]);
        }
        sb.append(" ");
        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
        Input in = new Input();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            String line = in.line();
            String[] strs = line.split(" ");
            for (String str : strs) {
                reversePrint(str);
            }
            System.out.println();
        }
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
