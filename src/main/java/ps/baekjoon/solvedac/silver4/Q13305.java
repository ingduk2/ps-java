package ps.baekjoon.solvedac.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 주유소
 * https://www.acmicpc.net/problem/13305
 *
 * 5 2 4 1 마지막 기름값은 의미가 없
 * 5 > 2, 5
 * 2 < 4, 2
 * 4 > 1, 이전값
 *
 * 1 2 4 5
 * 1 에서 다넣는게 제일 쌈
 * 1 < 2, 1
 * 2 < 4, 1
 * 4 < 5, 1
 *
 * 1 2 4 1
 * 1 < 2, 1
 * 2 < 4, 1
 * 4 > 1, 이전값 1
 *
 * 5 4 3 2 1
 * 5 > 4, 5
 * 4 > 3, 4
 * 3 > 2, 3
 * 2 > 1, 2
 *
 * 5 4 3 4 5
 * 5 > 4, 5
 * 4 > 3, 4
 * 3 < 4, 3
 * 4 < 5, 3
 *
 * 1 4 5 3
 * 1 < 4, 1
 * 4 < 5, 1
 * 5 > 3, 이전값
 *
 * 1 4 3 2
 * 1 < 4, 1
 * 4 > 3, 이전값. 1
 * 3 > 2, 이전값 1
 *
 * 1 4 3 4
 * 1 < 4, 1
 * 4 > 3, 이전값 1
 * 3 < 4, 저장값 1
 *
 * a > b 이면 이전값과 비교해서 작은값.
 * a < b 이면 저장값 없으면 a 사용 후 저장, 연속으로 작으면 저장값 사용하게
 *       저장값은 새로운 저장값 있을때 최소값
 *
 */
public class Q13305 {

    public static void main(String[] args) {

        Input in = new Input();
        int n = in.nextInt();
        int[] dists = new int[n];
        int[] oils = new int[n];

        for (int i = 0; i < n - 1; i++) {
            dists[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            oils[i] = in.nextInt();
        }

        long sum = 0;
        long minOil = Integer.MAX_VALUE;
        long prev = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            if (oils[i] > oils[i + 1]) {
                long val = Math.min(prev, oils[i]);
                sum += val * dists[i];
                prev = val;
            } else {
                minOil = Math.min(minOil, oils[i]);
                sum += minOil * dists[i];
                prev = minOil;
            }
        }

        System.out.println(sum);
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
