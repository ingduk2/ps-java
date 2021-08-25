package ps.baekjoon.solvedac.silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 로또
 * https://www.acmicpc.net/problem/6603
 */
public class Q6603 {

    private static void combination(int[] arr, boolean[] visited, int start, int m, int depth) {
        if (depth == m) {

            for (int i = 0; i < arr.length; i++) {
                if (visited[i]) System.out.print(arr[i] + " ");
            }
            System.out.println();

            return;
        }

        for (int i = start; i < arr.length; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, m, depth + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        Input in = new Input();
        while (true) {
            String[] s = in.line().split(" ");
            if (s[0].equals("0")) return;

            int k = Integer.parseInt(s[0]);
            int[] arr = new int[k];
            boolean[] visited = new boolean[k];
            for (int i = 1; i < s.length; i++) {
                arr[i - 1] = Integer.parseInt(s[i]);
            }

            combination(arr, visited, 0, 6, 0);
            System.out.println();
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

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
