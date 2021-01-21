package ps.thiscodingtest.previous_problems.dfs_bfs;

import java.util.*;

/**
 * 특정 거리의 도시 찾기
 */
/*
4 4 2 1
1 2
1 3
2 3
2 4
 */
public class Q15 {

    public static void bfs(List<List<Integer>> graph, int start, int[] distance) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int v = q.remove();
            for (Integer i : graph.get(v)) {
                if (distance[i] == -1) {
                    distance[i] = distance[v] + 1;
                    q.add(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int x = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
        }

        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1);

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
        }

        distance[x] = 0;
        bfs(graph, x, distance);

        boolean isResult = false;
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] == k) {
                System.out.println(i);
                isResult = true;
            }
        }

        if (!isResult) {
            System.out.println(-1);
        }
    }
}
