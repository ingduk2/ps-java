package ps.baekjoon.solvedac.silver1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 단지번호붙이기
 * https://www.acmicpc.net/problem/2667
 *
 * 1. 1인 경우 dfs를 돈다.
 * 2. 끝나면 단지수 cnt++;
 * 3. 집의 수 저장
 *
 * dfs, bfs
 */
public class Q2667 {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private int cnt = 0;
    private static List<Integer> homeCounts = new ArrayList<>();

    private static int dfs(int[][] arr, boolean[][] visited, int x, int y) {
        int homeCnt = 1;

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx > -1 && nx < arr.length && ny > -1 && ny < arr.length) {
                if (arr[nx][ny] == 1 && !visited[nx][ny]) {
                    homeCnt += dfs(arr, visited, nx, ny);
                }
            }
        }

        return homeCnt;
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            char[] chars = in.next().toCharArray();
            for (int j = 0; j < n; j++) {
                arr[i][j] = Character.getNumericValue(chars[j]);
            }
        }

        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    homeCounts.add(dfs(arr, visited, i, j));
                }
            }
        }

        System.out.println(homeCounts.size());
        Collections.sort(homeCounts);
        for (Integer homeCount : homeCounts) {
            System.out.println(homeCount);
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
