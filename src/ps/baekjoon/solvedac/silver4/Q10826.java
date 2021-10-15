package ps.baekjoon.solvedac.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * 피보나치 수 4
 * https://www.acmicpc.net/problem/10826
 *
 * 피보나치 100 = 354224848179261915075
 * 엄청 큰 수기 때문에 BigInteger 사용
 *
 * for문에 0 들어가면 array index 에러
 */
public class Q10826 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        if (n == 0) {
            System.out.println(0);
            return;
        }

        BigInteger[] d = new BigInteger[n + 1];
        d[0] = new BigInteger("0");
        d[1] = new BigInteger("1");


        for (int i = 2; i < n + 1; i++) {
            d[i] = d[i - 1].add(d[i - 2]);
        }

        System.out.println(d[n]);
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
