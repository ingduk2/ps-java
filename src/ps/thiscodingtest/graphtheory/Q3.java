package ps.thiscodingtest.graphtheory;

import java.util.*;

/**
 * 도시 분할 계획
 */
/*
7 12
1 2 3
1 3 2
3 2 1
2 5 2
3 4 4
7 3 6
5 1 5
1 6 2
6 4 1
6 5 3
4 5 3
6 7 4
 */
public class Q3 {

    private static class Edge {
        private int a;
        private int b;
        private int cost;

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getCost() {
            return cost;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();


        int[] parent = new int[n + 1];
        List<Edge> edges = new ArrayList<>();

        int result = 0;
        int last = 0;

        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }


        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            edges.add(new Edge(a, b, c));
        }

        edges.sort(Comparator.comparingInt(Edge::getCost));

        for (Edge edge : edges) {
            int a = edge.getA();
            int b = edge.getB();
            int c = edge.getCost();

            if (findParent(parent, a) != findParent(parent, b)) {
                unionParent(parent, a, b);
                result += c;
                // 마지막 가장 큰것 빼면 2개로 분리.
                last = c;
            }
        }

        System.out.println(result - last);
    }

    private static void unionParent(int[] parent, int a, int b) {
        a = findParent(parent, a);
        b = findParent(parent, b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private static int findParent(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = findParent(parent, parent[x]);
        }
        return parent[x];
    }

}
