package ps.become_a_successful_coding_test_candidate.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 40 배달

풀이
- 가중치가 있으므로 다익스트라 사용
- 양방향으로 통행 가능 (양방향)
 */
public class Problem40 {

    static class Solution1 {

        private static class Node {
            private int dest;
            private int cost;

            public Node(int dest, int cost) {
                this.dest = dest;
                this.cost = cost;
            }
        }

        public int solution(int N, int[][] road, int K) {
            List<Node>[] adjList = new ArrayList[N + 1];
            for (int i = 0; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int[] edge : road) {
                adjList[edge[0]].add(new Node(edge[1], edge[2]));
                adjList[edge[1]].add(new Node(edge[0], edge[2]));
            }

            int[] dist = new int[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            int start = 1;
            dist[start] = 0;

            Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
            pq.add(new Node(start, 0));

            while (!pq.isEmpty()) {
                Node now = pq.poll();

                if (dist[now.dest] < now.cost) {
                    continue;
                }

                for (Node next : adjList[now.dest]) {
                    if (dist[next.dest] > now.cost + next.cost) {
                        dist[next.dest] = now.cost + next.cost;
                        pq.add(new Node(next.dest, dist[next.dest]));
                    }
                }
            }

            int answer = 0;
            for (int i = 0; i <= N; i++) {
                if (dist[i] <= K) {
                    answer++;
                }
            }

            return answer;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        5,
                        new int[][]{
                                {1, 2, 1},
                                {2, 3, 3},
                                {5, 2, 2},
                                {1, 4, 2},
                                {5, 3, 1},
                                {5, 4, 2},
                        },
                        3,
                        4
                ),
                Arguments.of(
                        6,
                        new int[][]{
                                {1, 2, 1},
                                {1, 3, 2},
                                {2, 3, 2},
                                {3, 4, 3},
                                {3, 5, 2},
                                {3, 5, 3},
                                {5, 6, 1},
                        },
                        4,
                        4
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int N, int[][] road, int k, int expected) {
        assertThat(new Solution1().solution(N, road, k)).isEqualTo(expected);
    }
}
