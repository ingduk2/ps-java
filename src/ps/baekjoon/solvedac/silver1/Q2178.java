package ps.baekjoon.solvedac.silver1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 미로 탐색
 * https://www.acmicpc.net/problem/2178
 *
 * 1. 미로같은 경우 bfs 탐색
 * 2. x, y 좌표 vo 생성
 * 3. 거리를 저장해야 하는데 bfs 4방향 탐색 후 이전 거리 + 1 해준다.(새 좌표에)
 *
 * 재방문하지 않고
 * 한번 방문하고 체크 후 거리를 저장하기 때문에 목적지는 최소
 *
 * bfs
 */
public class Q2178 {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static int[][] distance;

    private static class Pos {
        private int x;
        private int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    private static void bfs(int[][] arr, boolean[][] visited, int n, int m) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0, 0));
        visited[0][0] = true;
        distance[0][0] = 1;

        while (!q.isEmpty()) {
            Pos poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                if (nx > -1 && nx < n && ny > -1 && ny < m) {
                    if (arr[nx][ny] == 1 && !visited[nx][ny]) {
                        distance[nx][ny] = distance[poll.x][poll.y] + 1;
                        visited[nx][ny] = true;
                        q.add(new Pos(nx, ny));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int m = in.nextInt();

        distance = new int[n][m];
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] next = in.next().toCharArray();
            for (int j = 0; j < m; j++) {
                arr[i][j] = Character.getNumericValue(next[j]);
            }
        }

        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }

        boolean[][] visited = new boolean[n][m];
        bfs(arr, visited, n, m);

        for (int[] ints : distance) {
            System.out.println(Arrays.toString(ints));
        }

        System.out.println(distance[n - 1][m - 1]);
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
