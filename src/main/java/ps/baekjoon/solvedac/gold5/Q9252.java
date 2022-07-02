package ps.baekjoon.solvedac.gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * LCS 2
 * https://www.acmicpc.net/problem/9252
 *
 * str1[i] == str2[j]
 * dp[i][j] = dp[i - 1][j - 1] + 1
 *
 * str1[i] != str2[j]
 * dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
 *
 * LCS를 출력하는 것은
 * 마지막에서 반대로 구한다.
 * 대각선에서 왔을때만 +1 이므로 이때 문자 +
 * dp[i][j] == dp[i - 1][j] 왼쪽에서 옴
 * dp[i][j] == dp[i][j - 1] 위에서 옴
 * else 나머지는 대각선에서 오는것
 *
 *
 */
public class Q9252 {

    public static void main(String[] args) {
        Input in = new Input();
        String n = in.next();
        String m = in.next();

        char[] str1 = n.toCharArray();
        char[] str2 = m.toCharArray();

        int[][]dp = new int[str1.length + 1][str2.length + 1];
        for (int i = 1; i < str1.length + 1; i++) {
            for (int j = 1; j < str2.length + 1; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[str1.length][str2.length]);

        StringBuilder lcs = new StringBuilder();
        int i = str1.length;
        int j = str2.length;
        while (i != 0 && j != 0) {
            if (dp[i][j] == dp[i - 1][j]) {
                i--;
            } else if (dp[i][j] == dp[i][j - 1]) {
                j--;
            } else {
                lcs.append(str1[i - 1]);
                i--;
                j--;
            }
        }

        System.out.println(lcs.reverse());
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
