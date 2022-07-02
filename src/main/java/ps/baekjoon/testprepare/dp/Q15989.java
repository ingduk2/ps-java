package ps.baekjoon.testprepare.dp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1, 2, 3 더하기 4
 * https://www.acmicpc.net/problem/15989
 *
 * 중복을 빼야한다.
 * 그래서 2차원 배열로 해야한다.
 * dp[n][1] 1로 시작, 1 이하의 수로 만드는 경우
 * dp[n][2] 2로 시작, 2 이하의 수로 만드는 경우
 * dp[n][3] 3로 시작, 3 이하의 수로 만드는 경
 *
 *  dp[n][1] dp[n][2] dp[n][3]
 * 1 1 0 0
 * 2 1 1 0
 * 3 1 1 1
 * 4 1 2 1
 * 5 1 2 2
 * 6 1 3 3
 * 7 1 3 4
 *
 * d[n][1] = 1
 * d[n][2] = d[n-2][2] + d[n-2][1] 4인 경우 2에다가(1 1 + 2, 2 + 2)
 * d[n][3] = d[n-3][1] + d[n-3][2] + d[n-3][3] 3작은 수에 3을 붙이는 의미
 *
 * 1 - 1 - 1
 * 2 - 1 1, 2 - 2
 * 3 - 1 1 1, 2 1, 3 - 3
 * 4 - 1 1 1 1, 2 1 1, 2 2, 3 1 - 4
 * 5 - 1 1 1 1 1, 2 1 1 1, 2 2 1, 3 1 1, 3 2 - 5
 * 6 - 1 1 1 1 1 1, 2 1 1 1, 2 2 1 1, 2 2 2 1, 3 1 1 1, 3 2 1, 3 3 - 7
 * 7 - 1 1 1 1 1 1 1, 2 1 1 1 1, 2 2 1 1 1, 2 2 2 1 1,  3 1 1 1 1, 3 2 1 1, 3 3 1, 3 2 2 - 8
 */
public class Q15989 {

    public static void main(String[] args) {
        Input in = new Input();
        int t = in.nextInt();


        int[][] dp = new int[10000 + 1][3 + 1];
        dp[1][1] = 1;

        dp[2][1] = 1;
        dp[2][2] = 1;

        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i < 10000 + 1; i++) {
            dp[i][1] = 1;
            dp[i][2] = dp[i - 2][2] + dp[i - 2][1];
            dp[i][3] = dp[i - 3][3] + dp[i - 3][2] + dp[i - 3][1];
        }

        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            System.out.println(dp[n][1] + dp[n][2] + dp[n][3]);
        }
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
