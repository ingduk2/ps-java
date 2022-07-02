package ps.baekjoon.solvedac.silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 분수 합
 * https://www.acmicpc.net/problem/1735
 *
 * 1. 최대공약수로 각각 나누기
 * 2. 분수 합 구하기
 *
 * 위처럼 하니 안되서
 * 1. 분수 합 구하기
 * 2. 최대공약수 구해서 각각 나누기
 *
 * 로 하니까 됨..
 * 먼저 공약수로 나누면 기약분수가 안됨.
 * 1 4 (gcd 1)
 * 1 4 (gcd 1)
 * 8 16
 *
 */
public class Q1735 {

    private static int gcd(int a, int b) {
        if (a <= b) {
            int temp = a;
            a = b;
            b = temp;
        }

        if (b == 0) return a;
        else return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Input in = new Input();
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();

        int numerator = a * d + b * c;
        int denominator = b * d;

        int gcd = gcd(denominator, numerator);
        System.out.println(numerator / gcd + " " + denominator / gcd);
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
