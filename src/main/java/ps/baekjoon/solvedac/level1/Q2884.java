package ps.baekjoon.solvedac.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 알람 시계
 * https://www.acmicpc.net/problem/2884
 *
 * 45분 빼기
 *
 * 먼저 분 -45 해서 양수면 그냥 m-=45
 * 음수면 시간 -1 후 m = 60 - 차이
 *
 * 시간이 음수면 h = 24 - 차이
 * 양수면 h -= 1
 */
public class Q2884 {

    public static void main(String[] args) {
        Input in = new Input();
        int h = in.nextInt();
        int m = in.nextInt();

        int diffM = m - 45;
        if (diffM < 0) {
            int diffH = h - 1;
            if (diffH < 0) {
                h = 24 - Math.abs(diffH);
            } else {
                h -= 1;
            }

            m = 60 - Math.abs(diffM);
        } else {
            m -= 45;
        }

        System.out.println(h + " " + m);
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
