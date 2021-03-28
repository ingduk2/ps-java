package ps.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 설탕 배달
 * https://www.acmicpc.net/problem/2839
 *
 * dp도 가능할듯.
 */
public class Q2839_DP {

    private static int solution(int n) {
        int[] d = new int[n + 10];
        Arrays.fill(d, -1);
        d[3] = 1;
        d[5] = 1;
        d[6] = 2;

        for (int i = 8; i <= n; i++) {
            if (d[i - 3] == -1) {
                d[i] = d[i - 5] + 1;

            } else if (d[i - 5] == -1) {
                d[i] = d[i - 3] + 1;

            } else {
                d[i] = Math.min(d[i - 3], d[i - 5]) + 1;

            }
        }

        return d[n];
    }

    public static void main(String[] args) {
        InputReader input = new InputReader(new InputStreamReader(System.in));
        int n = input.nextInt();

        System.out.println(solution(n));
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
