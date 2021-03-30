package ps.baekjoon.step.step8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 부녀회장이 될테야
 * https://www.acmicpc.net/problem/2775
 */
public class Q2775 {
    public static void main(String[] args) {
        InputReader in = new InputReader(new InputStreamReader(System.in));
        int t = in.nextInt();

        int[][] apart = new int[15][15];
        for (int i = 1; i <= 14; i++) {
            apart[0][i] = i;
            apart[i][1] = 1;
        }

        for (int i = 1; i <= 14; i++) {
            for (int j = 1; j <= 14; j++) {
                apart[i][j] = apart[i - 1][j] + apart[i][j - 1];
            }
        }

        while (t-- > 0) {
            int k = in.nextInt();
            int n = in.nextInt();
            System.out.println(apart[k][n]);
        }
    }

    private static class InputReader {
        private BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStreamReader inputStreamReader) {
            this.reader = new BufferedReader(inputStreamReader, 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
