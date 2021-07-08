package ps.baekjoon.solvedac.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 적어도 대부분의 배수
 * https://www.acmicpc.net/problem/1145
 */
public class Q1145 {

    public static void main(String[] args) {
        Input in = new Input();

        int[] numbers = new int[5];
        for (int i = 0; i < 5; i++) {
            numbers[i] = in.nextInt();
        }

        int result = 1;
        int count = 0;
        while (count < 3) {
            count = 0;
            for (int number : numbers) {
                if (result % number == 0) count++;
            }

            result++;
        }

        System.out.println(result - 1);
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


