package ps.baekjoon.solvedac.gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 알파벳
 * https://www.acmicpc.net/problem/1987
 *
 * 방문을 알파벳으로 알 수 있다.
 * 다시 돌아가서 방문해야 하므로 visited -> false 로 다시 변경
 *
 * dfs
 * backtracking
 */
public class Q1987 {

    private static int[] dx = {0, 1, 0,  -1};
    private static int[] dy = {1, 0, -1, 0};
    private static int result;

    private static void dfs(char[][] arr, boolean[] visited, int x, int y, int cnt) {

        result = Math.max(result, cnt);
        visited[arr[x][y] - 'A'] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx > -1 && ny > -1 && nx < arr.length && ny < arr[0].length) {
                int next = arr[nx][ny] - 'A';
                if (!visited[next]) {
                    visited[next] = true;
                    dfs(arr, visited, nx, ny, cnt + 1);
                    visited[next] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int r = in.nextInt();
        int c = in.nextInt();

        char[][] arr = new char[r][c];
        for (int i = 0; i < r; i++) {
            String line = in.next();
            for (int j = 0; j < c; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        boolean[] visited = new boolean[26];
        dfs(arr, visited, 0, 0, 1);
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
