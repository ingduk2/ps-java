package ps.become_a_successful_coding_test_candidate.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 01 가장 먼 노드

풀이
- bfs
- 배열을 인접 리스트로 변환 (양방향)
- 1번 노드를 시작으로 BFS 를 수행하여 각 노드까지의 거리를 계산
- max 거리를 찾음
- max 거리의 개수를 찾음
 */
public class GraphRecommendProblem1 {

    static class Solution1 {

        private List<Integer>[] adjList;
        private int[] distances;

        public int solution(int n, int[][] edge) {
            adjList = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int[] e : edge) {
                adjList[e[0]].add(e[1]);
                adjList[e[1]].add(e[0]);
            }

            distances = new int[n + 1];
            Arrays.fill(distances, -1);
            distances[1] = 0;

            bfs(1);

            int max = Arrays.stream(distances)
                    .max()
                    .getAsInt();

            return (int) Arrays.stream(distances)
                    .filter(it -> it == max)
                    .count();
        }

        private void bfs(int start) {
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(start);

            while (!queue.isEmpty()) {
                int now = queue.poll();

                for (int next : adjList[now]) {
                    if (distances[next] == -1) {
                        distances[next] = distances[now] + 1;
                        queue.add(next);
                    }
                }
            }
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        6,
                        new int[][]{
                                {3, 6},
                                {4, 3},
                                {3, 2},
                                {1, 3},
                                {1, 2},
                                {2, 4},
                                {5, 2}
                        },
                        3
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, int[][] edge, int expected) {
        assertThat(new Solution1().solution(n, edge)).isEqualTo(expected);
    }
}
