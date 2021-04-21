package ps.baekjoon.testprepare.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 소수 찾기
 * https://www.acmicpc.net/problem/1978
 */
public class Q1978 {

    private static boolean prime(int x) {
        if (x < 2) return false;

        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (prime(in.nextInt())) {
                cnt++;
            }
        }

        System.out.println(cnt);
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
