package ps.thiscodingtest.graphtheory;

import java.util.*;

/**
 * 위상 정렬
 */
public class Ex10_6_TopologySort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        int[] indegree = new int[v + 1];
        List<Integer>[] graph = new ArrayList[v + 1];
        for (int i = 0; i < v + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph[a].add(b);
            indegree[b] += 1;
        }

        //topology sort
        List<Integer> resultList = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        //처음 시작시 진입차수가 0인 노드를 큐에 삽입
        for (int i = 1; i < v + 1; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            //큐에서 하나 꺼내기
            int now = q.poll();
            resultList.add(now);
            //해당 원소와 연결된 노드들의 진입차수에서 1 뺴기
            for (Integer i : graph[now]) {
                indegree[i] -= 1;
                //새롭게 진입차수가 0이 되는 노드를 큐에 삽입
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }
        }

        for (Integer i : resultList) {
            System.out.print(i + " ");
        }

        /*
7 8
1 2
1 5
2 3
2 6
3 4
4 7
5 6
6 4
         */
    }
}
