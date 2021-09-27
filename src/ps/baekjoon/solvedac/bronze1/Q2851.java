package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 슈퍼마리오
 * https://www.acmicpc.net/problem/2851
 *
 * 합이 100이 안될때를 생각 못함
 *
 * 1. 100이 안되면 for문 다 돌고 합 출력
 * 2. 점수를 더하면서 100 이 넘으면
 * 3. 현재점수 - 100 , 100 - 이전점수 (점수 <= 100 이기 때문에 이전점수는 100보다 작음)
 * 4. 차이1 <= 차이2 이면 현재점수가 정답 (같으면 큰값)
 * 5. 차이1 > 차이2 이면 이전점수가 정답
 *
 */
public class Q2851 {
    private static int cnt = 10;

    public static void main(String[] args) {
        Input in = new Input();
        int score = 0;
        for (int i = 0; i < cnt; i++) {
            int mushroom = in.nextInt();
            score += mushroom;
            if (score >= 100) {
                int diff1 = score - 100;
                int diff2 = 100 - (score - mushroom);

                if (diff1 <= diff2) {
                    System.out.println(score);
                } else {
                    System.out.println(score - mushroom);
                }
                return;
            }
        }

        System.out.println(score);
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
