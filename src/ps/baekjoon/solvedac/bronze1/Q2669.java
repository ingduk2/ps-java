package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 직사각형 네개의 합집합의 면적 구하기
 * https://www.acmicpc.net/problem/2669
 *
 * 2차원 배열을 색칠하고
 * 그 크기를 구하자
 */
public class Q2669 {

    private static int pos = 101;
    private static int[][] arr;

    private static void fillSquare(int x1, int y1, int x2, int y2) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                arr[i][j] = 1;
            }
        }
    }

    private static int getSquareArea() {
        int area = 0;
        for (int i = 0; i < pos; i++) {
            for (int j = 0; j < pos; j++) {
                if (arr[i][j] == 1) area++;
            }
        }

        return area;
    }

    public static void main(String[] args) {
        Input in = new Input();
        arr = new int[pos][pos];
        for (int i = 0; i < 4; i++) {
            int y1 = in.nextInt();
            int x1 = in.nextInt();
            int y2 = in.nextInt();
            int x2 = in.nextInt();

            fillSquare(x1, y1, x2, y2);
        }

        System.out.println(getSquareArea());
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
