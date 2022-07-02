package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 직각삼각형
 * https://www.acmicpc.net/problem/4153
 *
 * a^2 + b^2 = c^2
 * 30000 입력 -> 900000000
 *
 * 입력이 순서대로 들어온다는 말이 없다.
 * 정렬 필요
 */
public class Q4153 {

    public static void main(String[] args) {
        Input in = new Input();
        int[] arr = new int[3];
        while (true) {
            arr[0] = in.nextInt();
            arr[1] = in.nextInt();
            arr[2] = in.nextInt();

            Arrays.sort(arr);

            int a = arr[0];
            int b = arr[1];
            int c = arr[2];

            if (a + b + c == 0) {
                return;
            }

            if (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }

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
