package ps.baekjoon.solvedac.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 색종이 만들기
 * https://www.acmicpc.net/problem/2630
 *
 * 분할정복
 */
public class Q2630 {

    private static int whiteCnt = 0;
    private static int blueCnt = 0;

    private static void paper(int[][] arr, int x, int y, int n) {
        if (check(arr, x, y, n)) {
            if (arr[x][y] == 0) whiteCnt++;
            else blueCnt++;
            return;
        }

        int size = n / 2;
        paper(arr, x, y, size); //1
        paper(arr, x, y + size, size); //2
        paper(arr, x + size, y, size); //3
        paper(arr, x + size, y + size, size); //4

    }

    private static boolean check(int[][] arr, int x, int y, int n) {
        int color = arr[x][y];

        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (arr[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        paper(arr,0, 0, n);
        System.out.println(whiteCnt);
        System.out.println(blueCnt);
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
