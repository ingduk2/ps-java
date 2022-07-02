package ps.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p11052 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int p[] = new int[n + 1];
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            p[i + 1] = Integer.parseInt(str[i]);
        }
        int d[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                d[i] = Math.max(d[i], p[j] + d[i - j]);
            }
        }
        System.out.println(d[n]);
    }
}
