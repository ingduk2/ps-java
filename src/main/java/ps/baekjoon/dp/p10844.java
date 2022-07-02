package ps.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p10844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] d = new int[100 + 1][10];
        // 길이 i , j 마지막 수
        // d[i][j] = d[i-1][j-1] (j-1 >=0) + d[i-1][j+1] (j+1 <= 9)
        for (int i = 1; i <= 9; i++) {
            d[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j - 1 >= 0) d[i][j] += d[i - 1][j - 1];
                if (j + 1 <= 9) d[i][j] += d[i - 1][j + 1];
                d[i][j] %= 1000000000;
            }
        }

        Long ret = 0L;
        for (int i = 0; i <= 9; i++) ret += d[n][i];
        ret %= 1000000000;

        System.out.println(ret);
    }
}
