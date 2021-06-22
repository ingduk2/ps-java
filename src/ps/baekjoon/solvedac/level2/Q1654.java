package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 랜선 자르기
 * https://www.acmicpc.net/problem/1654
 *
 * 이분탐색.
 * left 1, right max길이
 */
public class Q1654 {

    public static void main(String[] args) {
        Input in = new Input();
        int k = in.nextInt();
        int n = in.nextInt();

        int max = 0;
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = in.nextInt();
            max = Math.max(max, arr[i]);
        }

        long left = 1;
        long right = max;

        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;
            for (int i = 0; i < k; i++) {
                sum += arr[i] / mid;
            }

            if (sum >= n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(right);

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
    }
}
