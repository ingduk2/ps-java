package ps.thiscodingtest.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 게임 개발
 */
public class Q3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);


        line = br.readLine().split(" ");
        int a = Integer.parseInt(line[0]);
        int b = Integer.parseInt(line[1]);
        int d = Integer.parseInt(line[2]);

        int[][] maps = new int[n][m];
        int[][] checks = new int[n][m];
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                maps[i][j] = Integer.parseInt(line[j]);
            }
        }


        checks[a][b] = 1;
        int cnt = 1;
        //          북  동  남  서
        //          0 , 1, 2, 3
        int[] dx = {0,  1, 0, -1};
        int[] dy = {-1, 0, 1, 0};


        while (true) {
            for (int[] che : checks) {
                System.out.println(" = " + Arrays.toString(che));
            }
            System.out.println();

            //1. 왼쪽으로
            d -= 1;
            if (d == -1) {
                d = 3;
            }

            //2. 왼쪽에 안가본칸 있으면 전진. 없으면 다시 1번으로
            int nx = a + dx[d];
            int ny = b + dy[d];
            if (maps[nx][ny] == 0 && checks[nx][ny] == 0) {
                a = nx;
                b = ny;
                checks[nx][ny] = 1;
                cnt += 1;
            }

            //3. 4방향 모두 가본칸 , 바다로 된 칸인 경우 방향 유지 뒤로간다.
            // 뒤쪽 방향이 바다 1 인 경우에 종료.
            int roundCnt = 0;
            for (int i = 0; i < 4; i++) {
                nx = a + dx[i];
                ny = b + dy[i];
                if (maps[nx][ny] == 1 || checks[nx][ny] == 1) {
                    roundCnt += 1;
                }
            }

            if (roundCnt == 4) {
                nx = a - dx[d];
                ny = b - dy[d];
                if (maps[nx][ny] == 0) {
                    a = nx;
                    b = ny;
                } else {
                    break;
                }
            }

        }

        System.out.println("cnt = " + cnt);


//        4 4
//        1 1 0
//        1 1 1 1
//        1 0 0 1
//        1 1 0 1
//        1 1 1 1
    }
}
