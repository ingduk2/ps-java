package ps.baekjoon.solvedac.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 터렛
 * https://www.acmicpc.net/problem/1002
 *
 * 두 지점에서 원 -> 교점의 개수
 *
 * x1,y1 x2,y2, r1,r2
 * 무한대는 두 좌표가 같고, 거리도 같을 경우
 * x1 == x2 , y1 == y2, r1 == r2
 *
 * 두 점이 겹치는 경우
 * r1 + r2 > d(두 좌표의 거리)
 * r1 - r2 < d
 * r1 - r2 < d < r1 + r2
 *
 * 한 점이 겹치는 경우
 * r2 - r1 = d (내접) ||
 * r1 + r2 = d
 *
 *
 */
public class Q1002 {

    public static void main(String[] args) {
        Input in = new Input();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int r1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
            int r2 = in.nextInt();

            int result = 0;
            double d = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            if (x1 == x2 && y1 == y2 && r1 == r2) result = -1;
            else if (Math.abs(r1 - r2) < d && d < r1 + r2) result = 2;
            else if (Math.abs(r1 - r2) == d || r1 + r2 == d) result = 1;

            System.out.println(result);
        }
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
