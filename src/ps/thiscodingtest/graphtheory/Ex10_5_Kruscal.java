package ps.thiscodingtest.graphtheory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 크루스칼 알고리즘(최소 신장트리 알고리즘)
 */
public class Ex10_5_Kruscal {

    private static class Edge{
        private int cost;
        private int a;
        private int b;

        public Edge(int cost, int a, int b) {
            this.cost = cost;
            this.a = a;
            this.b = b;
        }

        public int getCost() {
            return cost;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "cost=" + cost +
                    ", a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        int[] parent = new int[v + 1];
        List<Edge> edges = new ArrayList<>();

        int result = 0;

        for (int i = 1; i < v + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();
            edges.add(new Edge(cost, a, b));
        }

//        edges.sort((o1, o2) -> o1.cost - o2.cost);
        edges.sort(Comparator.comparingInt(Edge::getCost));


        System.out.println(edges);

        for (Edge edge : edges) {
            int cost = edge.getCost();
            int a = edge.getA();
            int b = edge.getB();

            if (findParent(parent, a) != findParent(parent, b)) {
                unionParent(parent, a, b);
                result += cost;
            }
        }

        System.out.println(result);
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

    /*
7 9
1 2 29
1 5 75
2 3 35
2 6 34
3 4 7
4 6 23
4 7 13
5 6 53
6 7 25
 */
}
