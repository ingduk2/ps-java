package ps.thiscodingtest.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1이 될 때까지
 */
public class Q4 {
    public static void main(String[] args) throws IOException {
        // N -1
        // N / K (나누어 떨어질 때만.)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);

        int cnt = 0;
        while (n != 1) {
            if (n % k == 0) {
                n /= k;
            } else {
                n -= 1;
            }
            cnt += 1;
        }

        System.out.println("cnt = " + cnt);
    }
}
