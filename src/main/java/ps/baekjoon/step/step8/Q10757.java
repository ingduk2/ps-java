package ps.baekjoon.step.step8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * 큰 수 A+B
 * https://www.acmicpc.net/problem/10757
 */
public class Q10757 {
    public static void main(String[] args) {
        InputReader in = new InputReader(new InputStreamReader(System.in));
        String a = in.next();
        String b = in.next();
        BigInteger add = new BigInteger(a).add(new BigInteger(b));
        System.out.println(add.toString());
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
