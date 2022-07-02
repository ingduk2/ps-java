package ps.baekjoon.solvedac.bronze2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 하얀 칸
 * https://www.acmicpc.net/problem/1100
 *
 * 체스판을 int[][] 로 만든다.
 * [1, 0, 1, 0, 1, 0, 1, 0]
 * [0, 1, 0, 1, 0, 1, 0, 1]
 * [1, 0, 1, 0, 1, 0, 1, 0]
 * [0, 1, 0, 1, 0, 1, 0, 1]
 * [1, 0, 1, 0, 1, 0, 1, 0]
 * [0, 1, 0, 1, 0, 1, 0, 1]
 * [1, 0, 1, 0, 1, 0, 1, 0]
 * [0, 1, 0, 1, 0, 1, 0, 1]
 *
 * 입력받으면서 arr[i][j] == 1 && F 이면 cnt++
 */
public class Q1100 {

    private static int size = 8;

    public static void main(String[] args) throws IOException {
        Input in = new Input();
        // 1 whilte, 0 black
        // 체스판 만들기
        boolean isStartWhite = true;
        int[][] arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isStartWhite) {
                    if (j % 2 == 0) arr[i][j] = 1;
                } else {
                    if (j % 2 != 0) arr[i][j] = 1;
                }
            }

            isStartWhite = !isStartWhite;
        }

        int cnt = 0;
        for (int i = 0; i < size; i++) {
            char[] inputs = in.line().toCharArray();
            for (int j = 0; j < size; j++) {
                if (arr[i][j] == 1 && inputs[j] == 'F') cnt++;
            }
        }

        System.out.println(cnt);
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
