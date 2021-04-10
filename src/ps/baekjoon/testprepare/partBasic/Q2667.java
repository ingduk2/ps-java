package ps.baekjoon.testprepare.partBasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 단지번호붙이기
 * https://www.acmicpc.net/problem/2667
 *
 * dfs 같은데...
 * 한 점에서 dfs시작. (0이고 방문 안했으면)
 * dfs는 4방향으로 같은숫자이고, 방문 안했으면 반복.
 * count+= 으로 단지내 집의 수 쌓는다.
 * 단지내 집의 수 오름차순..
 */
public class Q2667 {

    static int[] nx = {-1, 0, 1, 0};
    static int[] ny = {0, 1, 0, -1};

    public static int dfs(int[][] map, boolean[][] visited, int x, int y) {
        int count = 1;
        visited[x][y] = true;
        for (int  i = 0;  i < 4;  i++) {
            int dx = nx[i] + x;
            int dy = ny[i] + y;

            if (dx > -1 && dx < map.length && dy > -1 && dy < map.length) {
                if (map[dx][dy] == map[x][y] && visited[dx][dy] == false) {
                    count += dfs(map, visited, dx, dy);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Input input = new Input();
        int n = input.nextInt();
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String next = input.next();
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(next.charAt(j)));
            }
        }

        boolean[][] visited = new boolean[n][n];
        List<Integer> homeCnts = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0 && visited[i][j] == false) {
                    int count = dfs(map, visited, i, j);
                    homeCnts.add(count);
                }
            }
        }

        System.out.println(homeCnts.size());
        Collections.sort(homeCnts);
        for (Integer homeCnt : homeCnts) {
            System.out.println(homeCnt);
        }
    }

    private static class Input{
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
