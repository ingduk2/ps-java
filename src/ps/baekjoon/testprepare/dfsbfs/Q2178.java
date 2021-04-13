package ps.baekjoon.testprepare.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 미로 탐색
 * https://www.acmicpc.net/problem/2178
 *
 * 미로같은 경우는 bfs로 주변부터 살피는게 빠름.
 * 최소거리는 거리 배열에 이전거리 + 1 로 계속 쌓음.
 * 한 점에서 갈수있는 곳이 여러곳 있어도 같은 거리로 계산하게됨.
 */
public class Q2178 {

    private static class Pos {
        private int x;
        private int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int[][] distance;

    private static void bfs(int[][] arr, boolean[][] visited) {
        visited[0][0] = true;
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0, 0));
        distance[0][0] = 1;

        while (!q.isEmpty()) {
            Pos pos = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + pos.getX();
                int ny = dy[i] + pos.getY();

                if (nx > -1 && nx < arr.length && ny > -1 && ny < arr[0].length) {
                    if (!visited[nx][ny] && arr[nx][ny] == 1) {
                        distance[nx][ny] = distance[pos.getX()][pos.getY()] + 1;
                        visited[nx][ny] = true;
                        q.add(new Pos(nx, ny));

                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        Input input = new Input();
        int n = input.nextInt();
        int m = input.nextInt();

        distance = new int[n][m];
        int[][] arr = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String next = input.next();
            for (int j = 0; j < m; j++) {
                arr[i][j] = Character.getNumericValue(next.charAt(j));
            }
        }


        bfs(arr, visited);

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
    }
}
