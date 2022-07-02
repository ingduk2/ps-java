package ps.baekjoon.step.step9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 베르트랑 공준
 * https://www.acmicpc.net/problem/4948
 */
public class Q4948 {

    public static void main(String[] args) {

        int max = 123456 * 2;
        boolean[] check = new boolean[max + 1];
        check[0] = check[1] = true;

        for (int i = 2; i * i <= max ; i++) {
            if (check[i] == false) {
                for (int j = i + i; j <= max ; j+=i) {
                    check[j] = true;
                }
            }
        }


        InputReader in = new InputReader(new InputStreamReader(System.in));
        while (true) {
            int n = in.nextInt();
            if (n == 0) break;

            int cnt = 0;
            for (int i = n + 1; i <= 2 * n; i++) {
                if (check[i] == false) {
                    cnt++;
                }
            }
            System.out.println(cnt);

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
