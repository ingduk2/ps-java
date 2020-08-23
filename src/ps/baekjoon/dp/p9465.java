package ps.baekjoon.dp;

import jdk.internal.util.xml.impl.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[][] a = new int[2][n + 1];
            int[] line1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] line2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int i = 0; i < 2; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == 0) a[i][j] = line1[j - 1];
                    if (i == 1) a[i][j] = line2[j - 1];
                }
            }


            int[][] d = new int[n + 1][3];
            for (int i = 1; i <= n; i++) {
                d[i][0] = Math.max(Math.max(d[i - 1][0], d[i - 1][1]), d[i - 1][2]);
                d[i][1] = Math.max(d[i - 1][0], d[i - 1][2]) + a[0][i];
                d[i][2] = Math.max(d[i - 1][0], d[i - 1][1]) + a[1][i];
            }
            System.out.println(Math.max(Math.max(d[n][0], d[n][1]), d[n][2]));
        }
    }


}
