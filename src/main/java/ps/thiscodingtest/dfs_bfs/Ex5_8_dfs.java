package ps.thiscodingtest.dfs_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex5_8_dfs {

    public void dfs(List<List<Integer>> graph, int v, boolean[] visited) {
        visited[v] = true;
        System.out.println("v = " + v);
        for (Integer i : graph.get(v)) {
            if (visited[i] == false) {
                dfs(graph, i, visited);
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
        new Ex5_8_dfs().dfs(graph, 1, visited);
    }
}
