package ps.baekjoon.solvedac.silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 링
 * https://www.acmicpc.net/problem/3036
 *
 * 최대공약수를 구해서 나눠서 표현
 */
public class Q3036 {

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        int firstRing = in.nextInt();
        for (int i = 0; i < n - 1; i++) {
            int ring = in.nextInt();
            int gcd = gcd(firstRing, ring);
            System.out.println(firstRing/gcd + "/" + ring/gcd);
        }
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
