package ps.thiscodingtest.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 미로 탈출
 */
public class Q4 {
    public static int[][] array;
    public static int n;
    public static int m;

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static class Loc{
        public int x;
        public int y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int bfs(int x, int y) {
        Queue<Loc> q = new LinkedList<>();
        q.add(new Loc(x, y));

        while (!q.isEmpty()) {
            Loc loc = q.remove();
            x = loc.x;
            y = loc.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (array[nx][ny] == 0) {
                    continue;
                }

                if (array[nx][ny] == 1) {
                    array[nx][ny] = array[x][y] + 1;
                    q.add(new Loc(nx, ny));
                }
            }
        }
        return array[n-1][m-1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] sizes = line.split(" ");
        n = Integer.parseInt(sizes[0]);
        m = Integer.parseInt(sizes[1]);

        array = new int[n][m];
        for (int i = 0; i < n; i++) {
            line = br.readLine();
            for (int j = 0; j < m; j++) {
                array[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }


        System.out.println(bfs(0, 0));
    }
}
