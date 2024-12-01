package ps.become_a_successful_coding_test_candidate.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 36 다익스트라 알고리즘

 */
public class Problem36 {

    static class Solution1 {

        private static class Node {
            private int dest;
            private int cost;

            public Node(int dest, int cost) {
                this.dest = dest;
                this.cost = cost;
            }
        }

        public int[] solution(int[][] graph, int start, int n) {
            // 인접 리스트를 저장할 ArrayList 초기화
            List<Node>[] adjList = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjList[i] = new ArrayList<>();
            }

            // graph 정보를 연결 리스트로 저장
            for (int[] edge : graph) {
                adjList[edge[0]].add(new Node(edge[1], edge[2]));
            }

            int[] dist = new int[n];
            // 모든 노드의 거리 값을 무한대로 초기화
            Arrays.fill(dist, Integer.MAX_VALUE);

            // 시작 노드의 거리 값은 0으로 초기화
            dist[start] = 0;

            // 우선순위 큐를 생성하고 시작 노드를 삽입
            Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
            pq.add(new Node(start, 0));

            while (!pq.isEmpty()) {
                // 현재 가장 거리가 짧은 노드를 가져옴
                Node now = pq.poll();

                // 만약 현재 노드의 거리 값이 큐에서 가져온 거리 값보다 크면, 해당 노드는 이미 방문한 것이므로 무시
                if (dist[now.dest] < now.cost) {
                    continue;
                }

                // 현재 노드와 인접한 노드들의 거리 값을 계산하여 업데이트
                for (Node next : adjList[now.dest]) {
                    // 기존에 발견했던 거리보다 더 짧은 거리를 발견하면 거리 값을 갱신하고 큐에 넣음
                    if (dist[next.dest] > now.cost + next.cost) {
                        dist[next.dest] = now.cost + next.cost;
                        pq.add(new Node(next.dest, dist[next.dest]));
                    }
                }
            }

            // 최단 거리를 담고 있는 배열을 반환
            return dist;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {0, 1, 9},
                                {0, 2, 3},
                                {1, 0, 5},
                                {2, 1, 1}
                        },
                        0, 3,
                        new int[]{0, 4, 3}
                ),
                Arguments.of(
                        new int[][]{
                                {0, 1, 1},
                                {1, 2, 5},
                                {2, 3, 1}
                        },
                        0, 4,
                        new int[]{0, 1, 6, 7}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[][] graph, int start, int n, int[] expected) {
        assertThat(new Solution1().solution(graph, start, n)).isEqualTo(expected);
    }
}
