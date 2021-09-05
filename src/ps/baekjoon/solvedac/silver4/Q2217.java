package ps.baekjoon.solvedac.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 로프
 * https://www.acmicpc.net/problem/2217
 *
 * 로프 최소값 * 2 최대중량.
 *
 * 5
 * 10
 * 12
 * 15
 * 16
 * 18
 *
 * 정렬
 * 10 12 15 16 18
 * 큰것부터
 * 18 * 1 = 18
 * 16 * 2 = 32
 * 15 * 3 = 45
 * 12 * 4 = 48
 * 10 * 5 = 50
 *
 */
public class Q2217 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        Arrays.sort(arr);

        int max = 0;
        int cnt = 1;
        for (int i = n - 1; i >= 0; i--) {
            max = Math.max(max, arr[i] * cnt);
            cnt++;
        }

        System.out.println(max);
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
