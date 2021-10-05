package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 지영 공주님의 마법 거울
 * https://www.acmicpc.net/problem/11586
 *
 * 1 그대로
 * 2 좌우 반전
 * 3 상하 반전
 */
public class Q11586 {

    private static void printReverseUD(char[][] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    private static void printReverseLR(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j >= 0; j--) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    private static void print(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        Input in = new Input();
        int n = in.nextInt();
        char[][] arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = in.line();
            for (int j = 0; j < n; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        int k = in.nextInt();
        if (k == 1) {
            print(arr);
        } else if (k == 2) {
            printReverseLR(arr);
        } else if (k == 3) {
            printReverseUD(arr);
        }
    }

    private static class Input {
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
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

        public long nextLong() { return Long.parseLong(next()); }

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
