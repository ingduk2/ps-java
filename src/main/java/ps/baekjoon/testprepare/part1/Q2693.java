package ps.baekjoon.testprepare.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N번째 큰 수
 * https://www.acmicpc.net/problem/2693
 */
public class Q2693 {

    public static void main(String[] args) {
        Input in = new Input();
        int t = in.nextInt();

        int[] arr = new int[10];
        for (int i = 0; i < t; i++) {

            for (int j = 0; j < 10; j++) {
                arr[j] = in.nextInt();
            }

            Arrays.sort(arr);
            System.out.println(arr[7]);
        }
    }

    private static class Input{
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
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
