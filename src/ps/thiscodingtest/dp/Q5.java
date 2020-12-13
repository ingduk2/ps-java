package ps.thiscodingtest.dp;

import javax.crypto.spec.PSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 효율적인 화폐 구성
 */
public class Q5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        int[] d = new int[m + 1];
        for (int i = 1; i < m + 1; i++) {
            d[i] = 10001;
        }

        while (n-- > 0) {
            int val = Integer.parseInt(br.readLine());
            for (int i = val; i < m + 1; i++) {
                d[i] = Math.min(d[i], d[i-val]+1);
            }
            System.out.println(Arrays.toString(d));
        }

        if (d[m] == 10001) {
            System.out.println(-1);
        } else {
            System.out.println(d[m]);
        }
    }
}
