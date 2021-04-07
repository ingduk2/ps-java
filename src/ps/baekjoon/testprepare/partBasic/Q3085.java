package ps.baekjoon.testprepare.partBasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 사탕 게임
 * https://www.acmicpc.net/problem/3085
 *
 * 전부 다니면서 바꿔봄.
 * 좌우로 하나씩 스왑, 위아래로 하나씩 스왑.
 * 그다음에 행,열에서 연속된 문자 최대 길이 구하기
 */
public class Q3085 {

    public static void main(String[] args) {
        Input input = new Input();
        int n = input.nextInt();

        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            String next = input.next();
            for (int j = 0; j < n; j++) {
                board[i][j] = next.charAt(j);
            }
        }


        int maxCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                // ->
                swapCol(board, i, j);
                maxCnt = Math.max(maxCnt, counting(board));

                swapCol(board, i, j);
                maxCnt = Math.max(maxCnt, counting(board));
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n; j++) {
                // v
                swapRow(board, i, j);
                maxCnt = Math.max(maxCnt, counting(board));

                swapRow(board, i, j);
                maxCnt = Math.max(maxCnt, counting(board));
            }
        }

        System.out.println(maxCnt);
    }


    private static int counting(char[][] board) {
        int len = board.length;
        int maxCnt = 0;

        //row
        for (int i = 0; i < len; i++) {
            int cnt = 1;
            char c = board[i][0];
            for (int j = 1; j < len; j++) {
                if (c == board[i][j]) {
                    cnt++;
                } else {
                    maxCnt = Math.max(maxCnt, cnt);
                    c = board[i][j];
                    cnt = 1;
                }
            }
            maxCnt = Math.max(maxCnt, cnt);
        }


        //col
        for (int i = 0; i < len; i++) {
            int cnt = 1;
            char c = board[0][i];
            for (int j = 1; j < len; j++) {
                if (c == board[j][i]) {
                    cnt++;
                } else {
                    maxCnt = Math.max(maxCnt, cnt);
                    c = board[j][i];
                    cnt = 1;
                }
            }
            maxCnt = Math.max(maxCnt, cnt);
        }

        return maxCnt;
    }

    private static void swapRow(char[][] board, int i, int j) {
        char temp = board[i][j];
        board[i][j] = board[i + 1][j];
        board[i + 1][j] = temp;
    }


    private static void swapCol(char[][] board, int i, int j) {
        char temp = board[i][j];
        board[i][j] = board[i][j + 1];
        board[i][j + 1] = temp;
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
