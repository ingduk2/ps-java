package ps.thiscodingtest.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 간단한 다익스트라 알고리즘
 */
public class Ex9_1 {

    public static class Node{
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
    }

    public static int n;
    public static int m;
    public static List<List<Node>> graph;
    public static boolean[] visited = new boolean[n + 1];
    public static int[] distance = new int[n + 1];

    public static int get_smallest_node() {
        int min_value = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 1; i < n + 1; i++) {
            if (distance[i] < min_value && visited[i] == false) {
                min_value = distance[i];
                index = i;
            }
        }
        return index;
    }

    public static void dijkstra(int start) {
        distance[start] = 0;
        visited[start] = true;
        for (Node n : graph.get(start)) {
            distance[n.getA()] = n.getB();
        }

        for (int i = 0; i < n - 1; i++) {
            int now = get_smallest_node();
            visited[now] = true;
            for (Node n : graph.get(now)) {
                int cost = distance[now] + n.getB();
                if (cost < distance[n.getA()]) {
                    distance[n.getA()] = cost;
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


        /*
6 11
1
1 2 2
1 3 5
1 4 1
2 3 3
2 4 2
3 2 3
3 6 5
4 3 3
4 5 1
5 3 1
5 6 2
         */
    }
}
