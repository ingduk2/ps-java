package ps.baekjoon.solvedac.gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 연구소
 * https://www.acmicpc.net/problem/14502
 *
 * 벽 3개를 꼭 세워야함
 * 1. 벽3개 세움
 * 2. 바이러스 퍼트리기
 * 3. 안전구역 개수 세기
 * 반복
 *
 * dfs
 */
public class Q14502 {

    private static int n;
    private static int m;
    private static int[][] arr;
    private static int[][] virusArr;
    private static int safeCnt = 0;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static int getSafeAreaCnt(int n, int m) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (virusArr[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    private static void deepCopy(int n, int m) {
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                virusArr[i][j] = arr[i][j];
//            }
//        }

        virusArr = Arrays.stream(arr).map(int[]::clone).toArray(int[][]::new);
    }

    private static void dfs(int i, int j) {
        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                if (virusArr[nx][ny] == 0) {
                    virusArr[nx][ny] = 2;
                    dfs(nx, ny);
                }
            }
        }
    }

    private static void solve(int count) {
        if (count == 3) {
            deepCopy(n, m);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (virusArr[i][j] == 2){
                        dfs(i, j);
                    }
                }
            }

            safeCnt = Math.max(safeCnt, getSafeAreaCnt(n, m));
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    arr[i][j] = 1;
                    solve(count + 1);
                    arr[i][j] = 0;
                }
            }
        }
    }



    public static void main(String[] args) {
        Input in = new Input();
        n = in.nextInt();
        m = in.nextInt();

        arr = new int[n][m];
        virusArr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        solve(0);
        System.out.println(safeCnt);
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

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
