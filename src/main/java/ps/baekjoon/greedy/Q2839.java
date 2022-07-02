package ps.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 설탕 배달
 * https://www.acmicpc.net/problem/2839
 *
 * 처음엔 경우별로 나누려고... (5로 나눠지면 최소, 5로 빼다가 3빼기, 등등..)
 * 소인수... 3^ + 5^ 한개씩... 안될듯
 * 5로 뺴다가 3나눠질때는 최소가 아님. 최대임.. 3 빼다가 5로 나눠야할듯.
 * dp도 가능할듯.
 */
public class Q2839 {

    private static int getModCount(int n, int mod) {
        int cnt = Integer.MAX_VALUE;
        if (n % mod == 0) {
            cnt = n / mod;
        }
        return cnt;
    }

    private static int getIterMinusCount(int n) {
        int cntIter = Integer.MAX_VALUE;
        int cntTemp = 0;
        while (n > 0) {
            n -= 3;
            cntTemp++;
            if (n % 5 == 0 && n > 0) {
                cntTemp += n / 5;
                cntIter = cntTemp;
                break;
            }
        }
        return cntIter;
    }

    private static int solution(int n) {
        int cnt3 = getModCount(n, 3);
        int cnt5 = getModCount(n, 5);
        int cntIter = getIterMinusCount(n);

        return Math.min(Math.min(cnt3, cnt5), cntIter);
    }

    public static void main(String[] args) {
        InputReader input = new InputReader(new InputStreamReader(System.in));
        int n = input.nextInt();

        int ret = solution(n);
        if (ret == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ret);
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
