package ps.thiscodingtest.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 음료수 얼려 먹기
 */
public class Q3 {
    public static int[][] array;
    public static int n;
    public static int m;

    public static boolean dfs(int x, int y) {
        if (x <= -1 || x >= n || y <= -1 || y >= m) {
            return false;
        }

        if (array[x][y] == 0) {
            array[x][y] = 1;
            // 상, 하, 좌, 우 dfs
            dfs(x, y - 1);
            dfs(x, y + 1);
            dfs(x - 1, y);
            dfs(x + 1, y);
            return true;
        }
        return false;
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

        for (int[] ints : array) {
            System.out.println("Arrays.toString(ints) = " + Arrays.toString(ints));
        }

        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(i, j) == true) {
                    ret += 1;
                }
            }
        }

        System.out.println("ret = " + ret);


        /*
15 14
00000111100000
11111101111110
11011101101110
11011101100000
11011111111111
11011111111100
11000000011111
01111111111111
00000000011111
01111111111000
00011111111000
00000001111000
11111111110011
11100011111111
11100011111111
         */
    }
}
