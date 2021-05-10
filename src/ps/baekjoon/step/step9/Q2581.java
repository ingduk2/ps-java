package ps.baekjoon.step.step9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 소수
 * https://www.acmicpc.net/problem/2581
 */
public class Q2581 {

    private static boolean prime(int x) {
        if (x < 2) {
            return false;
        }

        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(new InputStreamReader(System.in));
        int m = in.nextInt();
        int n = in.nextInt();

        int sum = 0;
        int min = Integer.MAX_VALUE;

        for (int i = m; i <= n; i++) {
            if (prime(i)) {
                sum += i;
                if (min == Integer.MAX_VALUE) min = i;
            }
        }

        if (sum == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sum);
            System.out.println(min);
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
