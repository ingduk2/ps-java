package ps.baekjoon.testprepare.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 전쟁 - 전투
 * https://www.acmicpc.net/problem/1303
 *
 * StringIndexOutOfBoundsException...
 * 왜나지..??
 *
 * 가로 n
 * 세로 m
 * 이므로 m먼저 돌리고, n 을 돌려야 한다.
 * 배열에서도 [m][n]
 */
public class Q1303 {

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static int dfs(char[][] arr, boolean[][] visited, int i, int j) {
        visited[i][j] = true;
        int sum = 1;

        for (int k = 0; k < 4; k++) {
            int nx = dx[k] + i;
            int ny = dy[k] + j;

            if (nx > -1 && nx < arr.length && ny > -1 && ny < arr[0].length) {
                if (visited[nx][ny] == false && arr[nx][ny] == arr[i][j])
                    sum += dfs(arr, visited, nx, ny);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Input input = new Input();
        int n = input.nextInt();
        int m = input.nextInt();

        char[][] arr = new char[m][n];
        for (int i = 0; i < m; i++) {
            String next = input.next();
            for (int j = 0; j < n; j++) {
                arr[i][j] = next.charAt(j);
            }
        }

        int wPower = 0;
        int bPower = 0;

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == false) {
                    int sum = dfs(arr, visited, i, j);
                    if (arr[i][j] == 'W') {
                        wPower += Math.pow(sum, 2);
                    } else {
                        bPower += Math.pow(sum, 2);
                    }
                }

            }
        }

        System.out.println(wPower + " " + bPower);

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
