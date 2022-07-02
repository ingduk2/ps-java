package ps.baekjoon.solvedac.silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 촌수계산
 * https://www.acmicpc.net/problem/2644
 *
 * 인접한 주변 검사하면서 가기때문에
 * dist[] 배열에 이전까지 거리 + 1 하면서 쌓
 *
 * dfs, bfs
 */
public class Q2644_BFS {

    private static void bfs(List<List<Integer>> graph, boolean[] visited, int[] distance, int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int v = q.poll();
            for (Integer i : graph.get(v)) {
                if (!visited[i]) {
                    visited[i] = true;
                    q.add(i);
                    distance[i] = distance[v] + 1;
                }
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
        int[] distance = new int[n + 1];
        bfs(graph, visited, distance, start);
        System.out.println(distance[end] != 0 ? distance[end] : -1);
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
