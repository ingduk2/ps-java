package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 약수들의 합
 * https://www.acmicpc.net/problem/9506
 */
public class Q9506 {

    private static int END = -1;

    private static void getDivisor(int num) {
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(num).append(" = ");
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0){
                sum += i;
                sb.append(i);
                if (sum < num) sb.append(" + ");
            }
        }

        if (sum == num) {
            System.out.println(sb);
        } else {
            System.out.println(num + " is NOT perfect.");
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        while (true) {
            int num = in.nextInt();
            if (num == -1) break;
            getDivisor(num);
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

        public long nextLong() { return Long.parseLong(next()); }

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
