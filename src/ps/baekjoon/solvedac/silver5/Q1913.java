package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 달팽이
 * https://www.acmicpc.net/problem/1913
 *
 * n = 3
 * 9 2 3
 * 8 1 4
 * 7 6 5
 *
 * 0,0 1,0 2,0 x++ x < n
 * 2,1 2,2 y++ y < n
 * 1,2 0,2 x--
 * 0,1 y--
 * 1,1 x++
 *
 *
 */
public class Q1913 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int target = in.nextInt();

        int[][] arr = new int[n][n];
        int start = (int) Math.pow(n, 2);
        int direction = 1;

        int x = -1;
        int y = 0;

        while (n > 0) {

            for (int dx = 0; dx < n; dx++) {
                x += direction;
                arr[x][y] = start--;
            }

            n -= 1;

            for (int dy = 0; dy < n; dy++) {
                y += direction;
                arr[x][y] = start--;
            }

            direction *= -1;
        }

        int targetX = 0;
        int targetY = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
                if (target == arr[i][j]) {
                    targetX = i + 1;
                    targetY = j + 1;
                }
            }
            System.out.println();
        }

        System.out.println(targetX + " " + targetY);

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
