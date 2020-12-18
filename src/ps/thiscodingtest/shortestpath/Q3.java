package ps.thiscodingtest.shortestpath;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 전보
 */
public class Q3 {

    public static class Node implements Comparable<Node>{
        private int a;
        private int b;

        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        @Override
        public int compareTo(Node o) {
            return this.a - o.a;
        }
    }

    public static void main(String[] args) {
        int INF = 100000000;

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int c = sc.nextInt();


        List<Node>[] graph = new ArrayList[n + 1];
        int[] distance = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            distance[i] = INF;
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();

            graph[x].add(new Node(y, z));
        }

        //dijkstra
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, c));
        distance[c] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int dist = node.getA();
            int now = node.getB();
            if (distance[now] < dist) {
                continue;
            }

            for (Node no : graph[now]) {
                int cost = dist + no.getB();
                if (cost < distance[no.getA()]) {
                    distance[no.getA()] = cost;
                    pq.add(new Node(cost, no.getA()));
                }
            }
        }

        int count = 0;
        int max_distance = 0;
        for (int d : distance) {
            if (d != INF) {
                count += 1;
                max_distance = Math.max(max_distance, d);
            }
        }

        System.out.println(count - 1 + " " + max_distance);
    }
}
