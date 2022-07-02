package ps.baekjoon.solvedac.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 보물
 * https://www.acmicpc.net/problem/1026
 *
 * A의 가장 작은 수와 B의 큰 수를 매칭하면될듯.
 * a reverse 정렬
 * b 정렬
 *
 * 덧셈
 *
 */
public class Q1026 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        Integer[] a = new Integer[n];
        Integer[] b = new Integer[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }

        Arrays.sort(a, Collections.reverseOrder());
        Arrays.sort(b);

        int result = 0;
        for (int i = 0; i < n; i++) {
            result += a[i] * b[i];
        }

        System.out.println(result);

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
