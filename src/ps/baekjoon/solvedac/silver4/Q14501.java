package ps.baekjoon.solvedac.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 퇴사
 * https://www.acmicpc.net/problem/14501
 *
 * i 날짜
 * n + 1 일까지
 *
 * i + t[i] 상담끝나는 날
 * if (i + t[i] <= n + 1) 상담가능
 *              오늘까지 가능수익, 이전까지 수익
 * dp[i+t[i]] = max(max+p[i], dp[i+t[i])
 *
 *      1일	2일	3일	4일	5일	6일	7일
 * Ti	3	5	1	1	2	4	2
 * Pi	10	20	10	20	15	40	200
 * d    0   0   0   10  30  0   45   45
 */
public class Q14501 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        int[] t = new int[n + 2];
        int[] p = new int[n + 2];
        int[] dp = new int[n + 2];

        for (int i = 1; i < n + 1; i++) {
            t[i] = in.nextInt();
            p[i] = in.nextInt();
        }

        int max = 0;
        for (int i = 1; i <= n + 1; i++) {
            max = Math.max(max, dp[i]);
            if (i + t[i] <= n + 1) {
                dp[i + t[i]] = Math.max(max + p[i], dp[i + t[i]]);
            }
        }

        System.out.println(max);
    }

    private static class Input {
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
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
