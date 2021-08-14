package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 색종이
 * https://www.acmicpc.net/problem/2563
 *
 * 처음 드는 생각.
 * 100 * 100 좌표 만들어서
 * 색종이 좌표에서 10 * 10 을 채워주기.
 *
 * 마지막에 arr 1 개수 카운트
 */
public class Q2563 {

    private static void fill(int[][] arr, int x, int y) {
        for (int i = x; i < x + 10; i++) {
            for (int j = y; j < y + 10; j++) {
                arr[i][j] = 1;
            }
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int cnt = in.nextInt();

        int[][] arr = new int[101][101];
        for (int i = 0; i < cnt; i++) {
            int x = in.nextInt();
            int y = in.nextInt();

            fill(arr, x, y);
        }

        int result = 0;
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (arr[i][j] == 1) result++;
            }
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
