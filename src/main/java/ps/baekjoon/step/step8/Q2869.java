package ps.baekjoon.step.step8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 달팽이는 올라가고 싶다
 * https://www.acmicpc.net/problem/2869
 * 식하나로 끝이 아니고 다른 경우가 있을 수 있다.
 * search
 */
public class Q2869 {
    public static void main(String[] args) {
        InputReader in = new InputReader(new InputStreamReader(System.in));
        int a = in.nextInt();
        int b = in.nextInt();
        int v = in.nextInt();
        // (a-b) * x + a > v
        // x > (v - a) / (a - b)

        if (a == v) {
            System.out.println(1);
        } else {
            int day = (v - a) / (a - b) + 1;
            if ((v - a) % (a - b) != 0) day ++;
            System.out.println(day);
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
