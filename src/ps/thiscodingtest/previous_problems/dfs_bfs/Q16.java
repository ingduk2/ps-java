package ps.thiscodingtest.previous_problems.dfs_bfs;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 연구소
 */
/*
7 7
2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0

4 6
0 0 0 0 0 0
1 0 0 0 0 2
1 1 1 0 0 2
0 0 0 0 0 2

8 8
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
2 0 0 0 0 0 0 2
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
 */
public class Q16 {

    private static int[][] map;
    private static int[][] temp;
    private static int n;
    private static int m;

    private static int[] dx = {0 , 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    private static int ret;

    //안전영역 크기 계산
    private static int getSafeArea() {
        int area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0) {
                    area += 1;
                }
            }
        }
        return area;
    }



    //바이러스 퍼트리기
    private static void virus(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (temp[nx][ny] == 0) {
                    temp[nx][ny] = 2;
                    virus(nx, ny);
                }
            }
        }
    }

    //벽 3 개 놓는 경우 전부다 조합
    private static void fence(int count) {

        if (count == 3) {

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    temp[i][j] = map[i][j];
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (temp[i][j] == 2) {
                        virus(i, j);
                    }
                }
            }

            if ( getSafeArea() != 0) {
                System.out.println("------------------------------------------");
                for (int[] ints : temp) {
                    System.out.println(Arrays.toString(ints));
                }
                System.out.println(getSafeArea());
                System.out.println("------------------------------------------");
            }
            ret = Math.max(ret, getSafeArea());
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 4;
                    count += 1;
                    fence(count);
                    map[i][j] = 0;
                    count -= 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        temp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        fence(0);
        System.out.println(ret);
    }
}
