package ps.baekjoon.solvedac.silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 나이트의 이동
 * https://www.acmicpc.net/problem/7562
 *
 * bfs
 */
public class Q7562 {
    private static class Pos{
        private int x;
        private int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int[] dx = {-1, -2, -2, -1, 1, 2, 2,  1};
    private static int[] dy = {-2, -1,  1,  2, 2, 1, -1, -2};

    private static int bfs(int[][] arr, boolean[][] visited, Pos current, Pos dest) {

        Queue<Pos> q = new LinkedList<>();
        q.add(current);
        visited[current.x][current.y] = true;

        while (!q.isEmpty()) {
            Pos pos = q.poll();

            if (dest.x == pos.x && dest.y == pos.y) {
                return arr[pos.x][pos.y];
            }

            for (int i = 0; i < 8; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];

                if (nx < 0 || nx >= arr.length || ny < 0 || ny >= arr.length) {
                    continue;
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    arr[nx][ny] = arr[pos.x][pos.y] + 1;
                    q.add(new Pos(nx, ny));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Input in = new Input();
        int t = in.nextInt();


        for (int i = 0; i < t; i++) {
            int l = in.nextInt();
            int[][] arr = new int[l][l];
            boolean[][] visited = new boolean[l][l];
            Pos current = new Pos(in.nextInt(), in.nextInt());
            Pos dest = new Pos(in.nextInt(), in.nextInt());

            System.out.println(bfs(arr, visited, current, dest));
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
