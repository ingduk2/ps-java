package ps.baekjoon.testprepare.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 바이러스
 * https://www.acmicpc.net/problem/2606
 *
 * 1번부터 dfs 해서 카운팅. 1번은 빼야하므로 -1
 *
 * 근데 런타임에러(IndexOutOfBounds) 가 나넴...
 * 1번부터 시작이므로 n 까지 하면 넘을듯.
 *
 * n + 1까지로 했더니 틀리네...
 *
 * 서로 연결되어있으므로 양방향인가
 * 그래프 문제같을때 양방향 주의.
 *
 *
 */
public class Q2606 {

    public static void main(String[] args) {
        Input input = new Input();
        int n = input.nextInt();
        int v = input.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < v; i++) {
            int a = input.nextInt();
            int b = input.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        boolean[] visited = new boolean[n + 1];
        int cnt = dfs(graph, visited, 1);
        System.out.println(cnt - 1);

    }

    private static int dfs(List<List<Integer>> graph, boolean[] visited, int start) {
        int cnt = 1;
        visited[start] = true;

        for (Integer i : graph.get(start)) {
            if (!visited[i]) {
                cnt += dfs(graph,visited, i);
            }
        }
        return cnt;
    }

    private static class Input {
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        private String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
