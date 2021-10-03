package ps.baekjoon.solvedac.bronze1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 딱지놀이
 * https://www.acmicpc.net/problem/14696
 *
 * ★ 4, ● 3, ■ 2, ▲ 1
 * if (4.cntA > 4.cntB)
 *   A Win
 *
 * if (4.cntA < 4.cntB)
 *   B win
 *
 * if (4.cntA == 4.cntB)
 *   if (3.cnt > ) win
 *
 *   if (3.cntA == 3.cntB)
 *      if(2.cnt > ) win
 *
 *      if (2.cntA == 2.cntB)
 *         if (1.cnt > ) win
 *
 *         if (1.cntA == 1.cntB)
 *            tie
 */
public class Q14696 {

    private static int DDAKJI_CNT = 5; // 4
    private static StringBuilder sb = new StringBuilder();

    private static void inputDDAKJI(int cnt, Input in, int[] arr) {
        for (int i = 0; i < cnt; i++) {
            arr[in.nextInt()]++;
        }
    }

    private static void solve(int[] arrA, int[] arrB) {
        char ch = 'D';
        for (int i = DDAKJI_CNT - 1; i > 0; i--) {
            if (arrA[i] > arrB[i]) {
                ch = 'A';
                break;
            } else if (arrA[i] < arrB[i]) {
                ch = 'B';
                break;
            }
        }
        sb.append(ch).append("\n");
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int[] arrA = new int[DDAKJI_CNT];
            inputDDAKJI(in.nextInt(), in, arrA);

            int[] arrB = new int[DDAKJI_CNT];
            inputDDAKJI(in.nextInt(), in, arrB);

            solve(arrA, arrB);
        }

        System.out.println(sb);
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
