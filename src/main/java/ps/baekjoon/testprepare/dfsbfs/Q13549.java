package ps.baekjoon.testprepare.dfsbfs;

import java.util.*;

/**
 * 숨바꼭질 3
 * https://www.acmicpc.net/problem/13549
 *
 * * bfs 로 n - 1, n + 1, n * 2 를 방문.
 * *              [5]
 * *          [4, 6, 10]
 * * [3, 5, 8] [5, 7, 12] [9, 11, 20]
 *
 * 숨바꼭질 1의 가장 빠른시간 구하는 방문하지 않았으면 1씩 쌓는 부분에서
 * *2 의 경우 이전 시간을 그대로 대입
 * 틀림. bfs의 경우 간선의 가중치가 같아야함. -> 이전 문제들은 1이었음.
 * 이번 문제는 0인 경우가 있어서 맞지 않다고함.
 *
 * https://www.acmicpc.net/board/view/55552
 *
 * 0 1 bfs 를 사용해보자.
 * deque
 * 가중치가 낮은 것부터 시작해야함. 안그래서 계속 틀림..
 * 처음값 check true 안해줘도 맞네.. 시작 끝 같으면 들어오자마자 0으로 끝나고
 * 시작 위치를 다시 지나서 최소가 될 수 없기 때문인것 같음.
 *
 * bfs로 푼 사람이 있음.
 * https://yabmoons.tistory.com/96
 */
public class Q13549 {

    private static int[] times = new int[100001];
    private static boolean[] check = new boolean[100001];

    private static class Edge {
        private int x;
        private int w;

        public Edge(int x, int w) {
            this.x = x;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "x=" + x +
                    ", w=" + w +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        Arrays.fill(times, Integer.MAX_VALUE);

        bfs(n, k);
        System.out.println(times[k]);
    }

    private static void bfs(int n, int k) {
        Deque<Integer> deque = new LinkedList<>();
        times[n] = 0;
        check[n] = true;
        deque.addLast(n);

        while (!deque.isEmpty()) {
            Integer x = deque.pollFirst();

            if (n == k) break;

            List<Edge> edges = List.of(new Edge(x * 2, 0), new Edge(x - 1, 1), new Edge(x + 1, 1));
            for (Edge edge : edges) {
                int v = edge.x;
                int w = edge.w;

                if (v >= 0 && v <= 100000 && check[v] == false) {
                    check[v] = true;
                    if (w == 1) {
                        times[v] = times[x] + 1;
                        deque.addLast(v);
                    } else {
                        times[v] = times[x];
                        deque.addFirst(v);
                    }
                }
            }
        }
    }
}
