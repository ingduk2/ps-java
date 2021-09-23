package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 빙고
 * https://www.acmicpc.net/problem/2578
 *
 * 1. check 배열 따로 두고 숫자 나올때마다 true 변경
 * 2. 5*5 이므로 전부다 센다. 행, 열, 대각선 check 배열 빙고 개수 카운트
 *
 * 2개에서 4개로 빙고수가 1씩증가하지 않으므로
 * 카운트 >= 로 해줘야함
 */
public class Q2578 {

    private static int n = 5;
    private static int BingoCnt = 3;

    private static int getColCnt(boolean[][] check) {
        int cnt = 0;
        boolean tile;

        for (int i = 0; i < n; i++) {
            tile = true;
            for (int j = 0; j < n; j++) {
                tile &= check[j][i];
            }

            if (tile) cnt++;
        }

        return cnt;
    }

    private static int getRowCnt(boolean[][] check) {
        int cnt = 0;
        boolean tile;

        for (int i = 0; i < n; i++) {
            tile = true;
            for (int j = 0; j < n; j++) {
                tile &= check[i][j];
            }

            if (tile) cnt++;
        }

        return cnt;
    }

    private static int getDiagCnt(boolean[][] check) {
        int cnt = 0;
        boolean tile = true;

        //대각선 체크
        for (int i = 0; i < n; i++) {
            tile &= check[i][i];
        }
        if (tile) cnt++;

        tile = true;
        for (int i = 0; i < n; i++) {
            tile &= check[i][4 - i];
        }
        if (tile) cnt++;

        return cnt;
    }

    private static int getCnt(boolean[][] check) {
       int cnt = 0;
       cnt += getColCnt(check);
       cnt += getDiagCnt(check);
       cnt += getRowCnt(check);

       return cnt;
    }

    private static void checkNumber(int[][] arr, boolean[][] check, int num) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == num) {
                    check[i][j] = true;
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int[][] arr = new int[n][n];
        boolean[][] check = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        for (int i = 0; i < n * n; i++) {
            int num = in.nextInt();
            checkNumber(arr, check, num);
            if (getCnt(check) >= BingoCnt) {
                System.out.println(i + 1);
                return;
            }
        }
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
