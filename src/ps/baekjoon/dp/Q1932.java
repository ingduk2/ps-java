package ps.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정수 삼각형
 * https://www.acmicpc.net/problem/1932
 *
 * dp 아래 또는 대각오른쪽
 * d[i + 1][j] , d[i + 1][j + 1]
 *
 * 숫자에서 반대로 가능한 최대를 찾는게 좋을듯
 * 다음 숫자에서 위, 대각왼쪽의 합중 max값을 계속 더해나감
 * d[i][j] = max(d[i - 1][j] + d[i][j], d[i - 1][j - 1] + d[i][j])
 * 범위 체크 문제..
 *
 *
 * 1. 삼각형이므로 j==0, j == i, 나머지 나눠서 처리
 * 첫번째 열은 위에것만 계산, i == j 인 경우에는 대각만, 나머지는 그중 최대값
 * j == 0 , d[i][j] += d[i - 1][j];
 * j == i , d[i][j] += d[i - 1][j - 1];
 * else , d[i][j] = Math.max(d[i - 1][j] + d[i][j], d[i - 1][j - 1] + d[i][j]);
 *
 * 2. 배열을 하나 더 크게 잡는다. n + 1
 * 그러면 첫번째 열도 대각을 0으로 계산할 수 있다.
 */
public class Q1932 {


    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int[][] d = new int[n][n];

        //input
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                d[i][j] = in.nextInt();
            }
        }

        //dp
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    d[i][j] += d[i - 1][j];
                } else if (j == i) {
                    d[i][j] += d[i - 1][j - 1];
                } else {
                    d[i][j] = Math.max(d[i - 1][j] + d[i][j], d[i - 1][j - 1] + d[i][j]);
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret, d[n - 1][i]);
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
