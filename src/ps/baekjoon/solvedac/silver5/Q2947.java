package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 나무 조각
 * https://www.acmicpc.net/problem/2947
 */
public class Q2947 {

    private static int CNT = 5;
    private static int[] checkArr = {1, 2, 3, 4, 5};

    private static boolean isCorrectArr(int[] arr) {
        for (int i = 0; i < CNT; i++) {
            if (arr[i] != checkArr[i]) return false;
        }

        return true;
    }

    private static void print(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    private static void solve(int[] arr) {
        for (int i = 0; i < CNT - 1; i++) {
            int temp;
            if (arr[i] > arr[i + 1]) {
                temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
                print(arr);
            }
        }

        //check
        if (!isCorrectArr(arr)) {
            solve(arr);
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int[] arr = new int[CNT];
        for (int i = 0; i < CNT; i++) {
            arr[i] = in.nextInt();
        }

        //solve
        solve(arr);
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
