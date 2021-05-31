package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 직사각형에서 탈출
 * https://www.acmicpc.net/problem/1085
 *
 * 6,2
 *
 * 직사각형 0,0 ~ 10, 3
 *
 * 직사각형 경계선이므로
 * min(w-x, h-y) 과
 * min(x-0, y-0) 의 최소값
 *
 */
public class Q1085 {

    public static void main(String[] args) {
        Input in = new Input();
        int x = in.nextInt();
        int y = in.nextInt();
        int w = in.nextInt();
        int h = in.nextInt();

        System.out.println(Math.min(Math.min(w-x, h-y), Math.min(x, y)));
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
