package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 체스판 다시 칠하기
 * https://www.acmicpc.net/problem/1018
 *
 * 8 * 8 잘르기.
 * WBWBWBWB , BWBWBWBW
 * ...      , ...
 *
 * 을 만들 수 있는 갯수 최소..?
 *
 * W시작 케이스와 비교, B시작 케이스와 비교.
 * 시작점을 옮겨가면서 전체 비교. n - 7까지, m - 7 까지
 */
public class Q1018 {

    private static char[][] case1 = {
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'}
    };

    private static char[][] case2 = {
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'}
    };

    private static int cnt = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int m = in.nextInt();

        char[][] arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            String words = in.next();
            for (int j = 0; j < m; j++) {
                arr[i][j] = words.charAt(j);
            }
        }

        //시작점 정해주기. 8개 이므로 -7로 1개 부터
        for (int i = 0; i < n - 7; i++) {
            for (int j = 0; j < m - 7; j++) {
                chess(arr, i, j);
            }
        }

        System.out.println(cnt);
    }

    private static void chess(char[][] arr,int x, int y) {
        int case1Cnt = 0;
        int case2Cnt = 0;
        for (int i = x; i < x + 8; i++) {
            for (int j = y; j < y + 8; j++) {
                //i - x, j - y
                if (arr[i][j] != case1[i - x][j - y]) {
                    case1Cnt++;
                }

                if (arr[i][j] != case2[i - x][j - y]) {
                    case2Cnt++;
                }
            }
        }

        cnt = Math.min(cnt, Math.min(case1Cnt, case2Cnt));
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
    }
}
