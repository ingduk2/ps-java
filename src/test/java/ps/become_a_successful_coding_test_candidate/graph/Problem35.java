package ps.become_a_successful_coding_test_candidate.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 35 너비 우선 탐색 순회
- bfs, Queue
 */
public class Problem35 {

    static class Solution1 {
        private List<Integer>[] adjList;
        private boolean[] visited;
        private List<Integer> answer;

        public int[] solution(int[][] graph, int start, int n) {
            adjList = new ArrayList[n + 1];
            for (int i = 0; i < adjList.length; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int[] edge : graph) {
                adjList[edge[0]].add(edge[1]);
            }

            visited = new boolean[n + 1];
            answer = new ArrayList<>();
            bfs(start);

            return answer.stream().mapToInt(Integer::intValue).toArray();
        }

        private void bfs(int start) {
            // 탐색 시 맨 처음 방문할 노드를 add 하고 방문 처리
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(start);
            visited[start] = true;

            // 큐가 비어 있지 않은 동안 반복
            while (!queue.isEmpty()) {
                // 큐에 있는 원소 중 가장 먼저 추가된 원소를 poll 하고 정답 리스트에 추가
                int now = queue.poll();
                answer.add(now);

                // 인접한 이웃 노드들에 대해서
                for (int next : adjList[now]) {
                    // 방문하지 않은 인접한 노드인 경우
                    if (!visited[next]) {
                        // 인접한 노드 방문 처리
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {1, 2},
                                {1, 3},
                                {2, 4},
                                {2, 5},
                                {3, 6},
                                {3, 7},
                                {4, 8},
                                {5, 8},
                                {6, 9},
                                {7, 9}
                        },
                        1, 9,
                        new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}
                ),
                Arguments.of(
                        new int[][]{
                                {1, 3},
                                {3, 4},
                                {3, 5},
                                {5, 2}
                        },
                        1, 5,
                        new int[]{1, 3, 4, 5, 2}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[][] graph, int start, int n, int[] expected) {
        assertThat(new Solution1().solution(graph, start, n)).isEqualTo(expected);
    }
}
