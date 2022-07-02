package ps.thiscodingtest.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 바닥 공사
 */
public class Q4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] d = new int[1000];
        d[0] = 1;
        d[1] = 3;
        for (int i = 2; i <= n; i++) {
            d[i] = (d[i-1] + d[i-2] * 2) % 796796;
        }

        System.out.println(d[n-1]);
    }
}
