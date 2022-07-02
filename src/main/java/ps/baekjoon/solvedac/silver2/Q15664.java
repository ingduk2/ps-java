package ps.baekjoon.solvedac.silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N과 M (10)
 * https://www.acmicpc.net/problem/15664
 *
 * 조합 비내림차순
 * 중복 빼줘야함(9) 번과 같은 방식으로
 *
 */
public class Q15664 {

    private static StringBuilder sb = new StringBuilder();

    private static void dfs(int[] arr, boolean[] visited, int start, int m, int depth) {
        if (m == depth) {
            for (int i = 0; i < arr.length; i++) {
                if (visited[i]) sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int before = 0;
        for (int i = start; i < arr.length; i++) {
            if (before != arr[i]) {
                visited[i] = true;
                before = arr[i];
                dfs(arr, visited, i + 1, m, depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int m = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        Arrays.sort(arr);

        boolean[] visited = new boolean[n];
        dfs(arr, visited, 0, m, 0);
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

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
