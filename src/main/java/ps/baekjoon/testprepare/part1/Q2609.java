package ps.baekjoon.testprepare.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 최대공약수와 최소공배수
 * https://www.acmicpc.net/problem/2609
 */
public class Q2609 {

    private static int getGCD(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return getGCD(b, a % b);
        }
    }

    private static int getLCM(int a, int b, int gcd) {
        return a * b / gcd;
    }

    public static void main(String[] args) {
        Input input = new Input();
        int a = input.nextInt();
        int b = input.nextInt();

        int gcd = getGCD(a, b);
        System.out.println(gcd);
        System.out.println(getLCM(a, b, gcd));

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
