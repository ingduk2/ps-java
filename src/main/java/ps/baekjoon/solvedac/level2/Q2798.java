package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 블랙잭
 * https://www.acmicpc.net/problem/2798
 * <p>
 * 100 개 이므로 조합 돌림.
 * m 이하인 합 구하기잭
 */
public class Q2798 {

    private static int result = 0;
    private static int m = 0;

    private static void combination(int[] cards, boolean[] visited, int start, int r, int depth) {
        if (depth == r) {

            int sum = 0;
            for (int i = 0; i < cards.length; i++) {
                if (visited[i]) {
                    sum += cards[i];
                }
            }

            if (sum <= m) {
                result = Math.max(result, sum);
            }

            return;
        }

        for (int i = start; i < cards.length; i++) {
            visited[i] = true;
            combination(cards, visited, i + 1, r, depth + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        m = in.nextInt();

        int[] cards = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            cards[i] = in.nextInt();
        }

        combination(cards, visited, 0, 3, 0);
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
    }
}
