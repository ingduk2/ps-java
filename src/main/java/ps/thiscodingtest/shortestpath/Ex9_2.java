package ps.thiscodingtest.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 개선된 다익스트라 알고리즘 PriorityQueue
 */
public class Ex9_2 {
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
        public String toString() {
            return "Node{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return this.a - o.a;
        }
    }

    public static int n;
    public static int m;
    public static List<List<Node>> graph;
    public static boolean[] visited = new boolean[n + 1];
    public static int[] distance = new int[n + 1];


    public static void dijkstra(int start) {
        PriorityQueue<Node> q = new PriorityQueue<>();

        q.add(new Node(0, start));
        distance[start] = 0;
        while (!q.isEmpty()) {
            //가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
            Node node = q.poll();
            int dist = node.getA();
            int now = node.getB();
            //현재 노드가 이미 처리된 적이 있는 노드라면 무시
            if (distance[now] < dist) {
                continue;
            }
            //현재 노드와 연결된 다른 인접 노드 확인
            for (Node n : graph.get(now)) {
                int cost = dist + n.getB();
                //현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                if (cost < distance[n.getA()]) {
                    distance[n.getA()] = cost;
                    q.add(new Node(cost, n.getA()));
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //노드의 개수, 간선의 개수를 입력받기
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);

        //시작 노드 번호 입력받기
        int start = Integer.parseInt(br.readLine());
        //노드 정보
        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        //체크 정보
        visited = new boolean[n + 1];
        //최단거리 테이블
        distance = new int[n + 1];
        //무한 초기화
        for (int i = 0; i < n + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        //노드 정보 입력받기
        for (int i = 0; i < m; i++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            int c = Integer.parseInt(line[2]);
            graph.get(a).add(new Node(b, c));
        }

        System.out.println(graph);
        dijkstra(start);

        for (int i = 1; i < n + 1; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }
}
