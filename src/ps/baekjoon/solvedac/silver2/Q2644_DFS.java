package ps.baekjoon.solvedac.silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 촌수계산
 * https://www.acmicpc.net/problem/2644
 *
 * start 부터 탐색 시작해서 경로마다 cnt++
 * if (start == end) 이면
 * cnt값 저장
 *
 * dfs, bfs
 */
public class Q2644_DFS {

    private static int result = -1;

    private static void dfs(List<List<Integer>> graph, boolean[] visited, int start, int end, int cnt) {
        visited[start] = true;

        if (start == end) {
            result = cnt;
            return;
        }

        for (Integer i : graph.get(start)) {
            if (!visited[i]) {
                dfs(graph, visited, i, end, cnt + 1);
            }
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int start = in.nextInt();
        int end = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        boolean[] visited = new boolean[n + 1];
        dfs(graph, visited, start, end, 0);
        System.out.println(result);
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
