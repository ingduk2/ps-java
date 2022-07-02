package ps.baekjoon.solvedac.gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 적록색약
 * https://www.acmicpc.net/problem/10026
 *
 * 적록색약 아닌사람 R G B
 * 적록색약인 사람 RG B
 *
 * 배열을 RG를 합쳐서만들어서 dfs 2번 돌림
 *
 * dfs
 */
public class Q10026 {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static void dfs(char[][] arr, boolean[][] visited, int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            char current = arr[x][y];
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < arr.length && ny < arr.length) {
                char next = arr[nx][ny];
                if (!visited[nx][ny] && current == next) {
                    dfs(arr, visited, nx, ny);
                }
            }
        }
    }

    private static int solve(char[][] arr, int n) {
        int cnt = 0;
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]){
                    dfs(arr, visited, i, j);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        char[][] arrN = new char[n][n];
        char[][] arrY = new char[n][n];

        for (int i = 0; i < n; i++) {
            char[] chars = in.next().toCharArray();
            for (int j = 0; j < chars.length; j++) {
                char c = chars[j];
                arrN[i][j] = c;
                arrY[i][j] = c;
                if (c == 'G') arrY[i][j] = 'R';
            }
        }

        int NoCnt = solve(arrN, n);
        int YesCnt = solve(arrY, n);

        System.out.println(NoCnt + " " + YesCnt);
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
