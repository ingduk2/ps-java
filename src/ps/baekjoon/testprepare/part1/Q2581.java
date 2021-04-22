package ps.baekjoon.testprepare.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 소수
 * https://www.acmicpc.net/problem/2581
 */
public class Q2581 {

    private static boolean prime(int x) {
        if (x < 2) return false;

        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Input in = new Input();
        int m = in.nextInt();
        int n = in.nextInt();


        int primeSum = 0;
        int primeMin = 0;
        for (int i = m; i <= n; i++) {
            if (prime(i)) {
                if (primeMin == 0) primeMin = i;

                primeSum += i;
            }
        }

        if (primeSum == 0) {
            System.out.println(-1);
            return;
        }

        System.out.println(primeSum);
        System.out.println(primeMin);
    }

    private static class Input{
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
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
