package ps.thiscodingtest.dfs_bfs;

import java.util.*;

public class Ex5_9_bfs {

    public void bfs(List<List<Integer>> graph, int start, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int v = q.remove();
            System.out.println("v = " + v);
            for (Integer i : graph.get(v)) {
                if (visited[i] == false) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList());
        graph.add(Arrays.asList(2, 3, 8));
        graph.add(Arrays.asList(1, 7));
        graph.add(Arrays.asList(1, 4, 5));
        graph.add(Arrays.asList(3, 5));
        graph.add(Arrays.asList(3, 4));
        graph.add(Arrays.asList(7));
        graph.add(Arrays.asList(2, 6, 8));
        graph.add(Arrays.asList(1, 7));
        for (List<Integer> integers : graph) {
            System.out.println("integers = " + integers);
        }

        boolean[] visited = new boolean[9];
        new Ex5_9_bfs().bfs(graph, 1, visited);
    }
}
