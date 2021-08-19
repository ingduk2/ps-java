package ps.baekjoon.solvedac.silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 예산
 * https://www.acmicpc.net/problem/2512
 *
 * 최대 큰것을 1씩 빼면서 총예산 만족하면 break
 */
public class Q2512 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        int sum = 0;
        int max = 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            sum += arr[i];
            max = Math.max(max, arr[i]);
        }

        int m = in.nextInt();

        //모두 허용 가능
        if (sum > m) {
            while (true) {
                int budget = 0;
                for (int i : arr) {
                    if (i > max) budget += max;
                    else budget += i;
                }

                if (budget <= m) break;
                max--;
            }
        }

        System.out.println(max);

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
