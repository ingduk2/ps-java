package ps.thiscodingtest.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1로 만들기
 */
public class Q2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int x = Integer.parseInt(s);

        int[] d = new int[30001];

        for (int i = 2; i < x + 1; i++) {
            d[i] = d[i - 1] + 1;
            if (i % 2 == 0) {
                d[i] = Math.min(d[i], d[i/2] + 1);
            }
            if (i % 3 == 0) {
                d[i] = Math.min(d[i], d[i/3] + 1);
            }
            if (i % 5 == 0) {
                d[i] = Math.min(d[i], d[i/5] + 1);
            }
        }

        for (int i = 1; i <= x; i++) {
            System.out.print(d[i] + " ");
        }
        System.out.println(d[x]);
    }
}
