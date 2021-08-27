package ps.baekjoon.solvedac.silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 상자넣기
 * https://www.acmicpc.net/problem/1965
 *
 * LIS
 * arr 1 6     2      5      7      3      5      6
 *     d[0]+1 d[0]+1 d[2]+1 d[3]+1 d[2]+1 d[5]+1 d[6]+1
 * dp  1 2     2      3      4      3      4      5
 *
 * arr i 순서대로
 * j < i 까지 돌면서
 * arr[j] < arr[i] && dp[i] < dp[j] + 1
 * 숫자가 더 작고, 작은숫자 상자값 + 1 > 큰숫자 상자값이면
 * dp[i] = dp[j] + 1 로 쌓아나간다.
 *
 * dp
 */
public class Q1965 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int[] arr = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            dp[i] = 1;
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
                    System.out.println(i + " " + j);
                    dp[i] = dp[j] + 1;
                    System.out.println(Arrays.toString(dp));
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
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
