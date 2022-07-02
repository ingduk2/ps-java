package ps.baekjoon.testprepare.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 음식물 피하기
 * https://www.acmicpc.net/problem/1743
 *
 * dfs로 연결되어있는 쓰레기 중 큰것 구했는데 뭔가 틀리다고함..흠
 * 좌표가 1부터 시작하는듯.. 예제에서 길이 3인데 3, 1 좌표가 나옴.
 */
public class Q1743 {

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static int dfs(int[][] arr, boolean[][] visited, int i, int j) {
        int sum = 1;
        visited[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int nx = dx[k] + i;
            int ny = dy[k] + j;

            if (nx > -1 && nx < arr.length && ny > -1 && ny < arr[0].length) {
                if (visited[nx][ny] == false && arr[nx][ny] == 1) {
                    sum+= dfs(arr, visited, nx, ny);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Input input = new Input();
        int n = input.nextInt();
        int m = input.nextInt();
        int k = input.nextInt();

        int[][] arr = new int[n + 1][m + 1];
        for (int i = 0; i < k; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            arr[x][y] = 1;
        }

        boolean[][] visited = new boolean[n + 1][m + 1];

        int maxTrash = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (arr[i][j] == 1 && visited[i][j] == false) {
                    maxTrash = Math.max(maxTrash, dfs(arr, visited, i, j));
                }
            }
        }

        System.out.println(maxTrash);
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
