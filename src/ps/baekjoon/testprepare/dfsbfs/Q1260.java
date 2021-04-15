package ps.baekjoon.testprepare.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * DFS와 BFS
 * https://www.acmicpc.net/problem/1260
 *
 * 양방향이므로 입력시 양쪽으로 add 필요
 * 작은것부터 방문한다고 했으므로 간선들 정렬 필요.
 */
public class Q1260 {

    private static void dfs(List<List<Integer>> graph, int start, boolean[] visited) {
        visited[start] = true;
        System.out.print(start + " ");
        for (int i : graph.get(start)) {
            if (visited[i] == false) {
                dfs(graph, i, visited);
            }
        }
    }

    private static void bfs(List<List<Integer>> graph, int start, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int v = q.poll();
            System.out.print(v + " ");
            for (int i : graph.get(v)) {
                if (visited[i] == false) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }

    }

    public static void main(String[] args) {
        Input input = new Input();
        int n = input.nextInt();
        int m = input.nextInt();
        int v = input.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = input.nextInt();
            int b = input.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (List<Integer> integers : graph) {
            Collections.sort(integers);
        }

        boolean[] visited = new boolean[n + 1];
        dfs(graph, v, visited);

        System.out.println();

        visited = new boolean[n + 1];
        bfs(graph, v, visited);
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
