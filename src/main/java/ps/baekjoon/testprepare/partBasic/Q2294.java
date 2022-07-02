package ps.baekjoon.testprepare.partBasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 동전2
 * https://www.acmicpc.net/problem/2294
 * d[0] = 0 처음에 d[5-5] 일 경우 1개 만들기위해서
 * d[j] = min(d[j], d[j-동전] + 1)
 */
public class Q2294 {

    public static void main(String[] args) {
        Input input = new Input();
        int n = input.nextInt();
        int k = input.nextInt();

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = input.nextInt();
        }

        int[] dp = new int[k + 1];
        Arrays.fill(dp, 10001);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j < k + 1; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }

        if (dp[k] == 10001) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }

    }

    private static class Input{
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
