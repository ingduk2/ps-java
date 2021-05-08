package ps.baekjoon.testprepare.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 점프
 * https://www.acmicpc.net/problem/1890
 *
 * 숫자만큼 오른쪽 또는 아래로..
 * 오른쪽 y+, 아래 x+
 *
 * 범위 체크 필요, n - 1, n - 1은 제외.(마지막에 2번 더 더해짐)
 *
 * n 이동값
 * ++ 로 늘리는게 아니라 경로에 쌓인 경우의수를 계속 더해줘야함
 * dp[i + n][j + n] = dp[i + n][j + n] + dp[i][j]
 *
 * dfs + dp 로 푸는 방법
 * dp로만 푸는 방법.
 */
public class Q1890 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int[][] board = new int[n][n];
        long[][] dp = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = in.nextInt();
            }
        }

        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (i == n - 1 && j == n - 1) break;

                if (dp[i][j] != 0) {
                    int num = board[i][j];
                    if (i + num < n) dp[i + num][j] += dp[i][j];
                    if (j + num < n) dp[i][j + num] += dp[i][j];
                }
            }
        }

        System.out.println(dp[n - 1][n - 1]);
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
    }
}
