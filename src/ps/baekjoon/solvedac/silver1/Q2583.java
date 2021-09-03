package ps.baekjoon.solvedac.silver1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 영역 구하기
 * https://www.acmicpc.net/problem/2583
 *
 * 사각형은
 * x1,y1 x2,y2 표현은
 * for x1 < x2
 *   for y1 < y2
 * 시작좌표부터 끝좌표까지 이중포문으로 표현
 *
 * dfs
 */
public class Q2583 {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static Integer dfs(int[][] arr, boolean[][] visited, int x, int y) {
        int cnt = 1;
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx > -1 && ny > -1 && nx < arr.length && ny < arr[0].length) {
                if (!visited[nx][ny] && arr[nx][ny] == 0) {
                    cnt += dfs(arr, visited, nx, ny);
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        Input in = new Input();
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();

        int[][] arr = new int[m][n];
        for (int i = 0; i < k; i++) {
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();

            for (int j = x1; j < x2; j++) {
                for (int l = y1; l < y2; l++) {
                    arr[l][j] ++;
                }
            }
        }

        boolean[][] visited = new boolean[m][n];
        int area = 0;
        List<Integer> areas = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && arr[i][j] == 0) {
                    areas.add(dfs(arr, visited, i, j));
                    area++;
                }
            }
        }

        System.out.println(area);
        Collections.sort(areas);
        for (Integer a : areas) {
            System.out.print(a + " ");
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

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
