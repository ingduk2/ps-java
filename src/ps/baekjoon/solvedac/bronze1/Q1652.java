package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 누울 자리를 찾아라
 * https://www.acmicpc.net/problem/1652
 *
 * X가 있다면 2개이상 가능.
 * X가 없다면 1개까지 가능.(벽에 닿음)
 * ..X 로 돌리면서 ++
 * 끝에 벽을만들자.
 */
public class Q1652 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        char[][] arr = new char[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            String next = in.next();
            for (int j = 0; j < next.length(); j++) {
                arr[i][j] = next.charAt(j);
            }
            arr[i][n] = 'X';
            arr[n][i] = 'X';
        }

        int row = 0;
        int col = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (arr[i][j] == '.' && arr[i][j + 1] == '.' && arr[i][j + 2] == 'X') row++;
                if (arr[j][i] == '.' && arr[j + 1][i] == '.' && arr[j + 2][i] == 'X') col++;
            }
        }

        System.out.println(row + " " + col);
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
