package ps.programmers.summer_winter_coding2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 배달
 * https://programmers.co.kr/learn/courses/30/lessons/12978
 *
 * dijkstra, 양방향 넣어줌
 */
public class Q12978 {

    private static class Node implements Comparable<Node>{
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

        @Override
        public String toString() {
            return "Node{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }


    public static List<List<Node>> graph;
    public static boolean[] visited;
    public static int[] distance;

    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        int m = road.length;

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[N + 1];
        distance = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int c = r[2];
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        dijkstra(1);

        long count = Arrays.stream(distance).filter(d -> d <= K).count();
        return Math.toIntExact(count);
    }

    private void dijkstra(int start) {
        PriorityQueue<Node> q = new PriorityQueue<>();

        q.add(new Node(0, start));
        distance[start] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int dist = node.getA();
            int now = node.getB();

            if (distance[now] < dist) {
                continue;
            }

            for (Node n : graph.get(now)) {
                int cost = dist + n.getB();
                if (cost < distance[n.getA()]) {
                    distance[n.getA()] = cost;
                    q.add(new Node(cost, n.getA()));
                }
            }
        }

    }


    public static void main(String[] args) {
        int n = 5;
        int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int k = 3;	//4
        System.out.println(new Q12978().solution(n, road, k));

        n = 6;
        road = new int[][]{{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}};
        k = 4; //	4
        System.out.println(new Q12978().solution(n, road, k));
    }
}