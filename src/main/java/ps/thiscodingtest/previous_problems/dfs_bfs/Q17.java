package ps.thiscodingtest.previous_problems.dfs_bfs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 경쟁적 전염
 */
/*
3 3
1 0 2
0 0 0
3 0 0
2 3 2

3 3
1 0 2
0 0 0
3 0 0
1 2 2
 */
public class Q17 {

    private static class Virus{
        private int num;
        private int sec;
        private int x;
        private int y;

        public Virus(int num, int sec, int x, int y) {
            this.num = num;
            this.sec = sec;
            this.x = x;
            this.y = y;
        }

        public int getNum() {
            return num;
        }

        public int getSec() {
            return sec;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Virus{" +
                    "num=" + num +
                    ", sec=" + sec +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }


    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[][] map = new int[n][n];
        Queue<Virus> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] != 0) {
                    queue.add(new Virus(map[i][j], 0, i, j));
                }
            }
        }

        List<Virus> collect = queue.stream().sorted(Comparator.comparingInt(Virus::getNum)).collect(Collectors.toList());

        int s = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();

        for (int[] ints : map) {
            System.out.println(Arrays.toString(ints));
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        while (!collect.isEmpty()) {
            System.out.println(collect);
            Virus virus = collect.get(0);
            int num = virus.getNum();
            int sec = virus.getSec();
            int vx = virus.getX();
            int vy = virus.getY();

            collect.remove(0);
            if (sec == s) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = vx + dx[i];
                int ny = vy + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (map[nx][ny] == 0) {
                        map[nx][ny] = num;
                        collect.add(new Virus(num, sec + 1, nx, ny));
                    }
                }
            }
        }
        for (int[] ints : map) {
            System.out.println(Arrays.toString(ints));
        }

        System.out.println(map[x - 1][y - 1]);
    }
}
