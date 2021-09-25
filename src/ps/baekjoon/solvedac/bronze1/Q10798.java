package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 세로 읽기
 * https://www.acmicpc.net/problem/10798
 *
 * A
 * BC
 * D
 * E
 * FGH
 *
 * 각 줄의 시작과 마지막에 빈칸은 없는게 문자가 없는것을 말하는듯.
 * char[] 초기값이 '\u0000'
*/
public class Q10798 {

    private static char[][] words = new char[5][15];
    private static char defaultChar = '\u0000';

    public static void main(String[] args) throws IOException {
        Input in = new Input();
        for (int i = 0; i < 5; i++) {
            char[] chars = in.line().toCharArray();
            System.arraycopy(chars, 0, words[i], 0, chars.length);
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if (words[j][i] != defaultChar) System.out.print(words[j][i]);
            }
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
