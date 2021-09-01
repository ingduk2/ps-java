package ps.baekjoon.solvedac.silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 유기농배추
 * https://www.acmicpc.net/problem/1012
 *
 * 1 나오고 visited false 면 그 위치에서
 * 4방향 dfs visited true로 바꿔줌
 *
 * dfs
 */
public class Q1012 {

    private static int[] dx = {0, 1, 0,  -1};
    private static int[] dy = {1, 0, -1, 0};

    private static void dfs(int[][] arr, boolean[][] visited, int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < arr.length && ny < arr[0].length) {
                if (!visited[nx][ny] && arr[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    dfs(arr, visited, nx, ny);
                }
            }
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int m = in.nextInt();
            int n = in.nextInt();
            int k = in.nextInt();
            int[][] arr = new int[m][n];
            for (int j = 0; j < k; j++) {
                arr[in.nextInt()][in.nextInt()] = 1;
            }

            boolean[][] visited = new boolean[m][n];
            int cnt = 0;
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    if (arr[x][y] == 1 && !visited[x][y]) {
                        dfs(arr, visited, x, y);
                        cnt++;
                    }
                }
            }

            System.out.println(cnt);

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
