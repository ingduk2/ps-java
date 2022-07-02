package ps.baekjoon.testprepare.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 퇴사 2
 * https://www.acmicpc.net/problem/15486
 *
 * dp문제
 * n + 1 >= day + t[day] 돈받는 날짜가 퇴사날보다 작거나 같을떄. 돈을 다음날에 받는다.
 * ret = max(ret,dp[day])
 * dp[day + t[day]] = max(ret + p[day], dp[day + t[day])
 *
 * 어렵다..
 */
public class Q15486 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        //7일일 경우 8이 필요.
        int[] t = new int[n + 2];
        int[] p = new int[n + 2];
        int[] dp = new int[n + 2];

        for (int i = 1; i < n + 1; i++) {
            t[i] = in.nextInt();
            p[i] = in.nextInt();
        }

        int ret = 0;
        for (int i = 1; i <= n + 1; i++) {
            System.out.println(i);
            System.out.println(Arrays.toString(dp));
            ret = Math.max(ret, dp[i]);
            if (n + 1 >= i + t[i]) {
                dp[i + t[i]] = Math.max(ret + p[i], dp[i + t[i]]);
            }
        }

        System.out.println(ret);

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
