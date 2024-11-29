package ps.become_a_successful_coding_test_candidate.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 34 깊이 우선 탐색 순회
- 인접 리스트
 */
public class Problem34 {

    static class Solution1 {

        private List<Integer>[] adjList;

        private boolean[] visited;
        private List<Integer> answer;

        public int[] solution(int[][] graph, int start, int n) {
            // 인접 리스트 초기화
            adjList = new ArrayList[n + 1];
            for (int i = 0; i < adjList.length; i++) {
                adjList[i] = new ArrayList<>();
            }

            // 그래프를 인접 리스트로 변환
            for (int[] edge : graph) {
                adjList[edge[0]].add(edge[1]);
            }

            visited = new boolean[n + 1];
            answer = new ArrayList<>();
            dfs(start);

            return answer.stream().mapToInt(Integer::intValue).toArray();
        }

        private void dfs(int now) {
            // 현재 노드를 방문 했음을 저장
            visited[now] = true;
            // 현재 노드를 결과 리스트에 추가
            answer.add(now);

            // 현재 노드와 인접한 노드 순회
            for (int next : adjList[now]) {
                if (!visited[next]) {
                    dfs(next);
                }
            }
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {1, 2},
                                {2, 3},
                                {3, 4},
                                {4, 5}
                        },
                        1, 5,
                        new int[]{1, 2, 3, 4, 5}
                ),
                Arguments.of(
                        new int[][]{
                                {1, 2},
                                {1, 3},
                                {2, 4},
                                {2, 5},
                                {3, 6},
                                {5, 6}
                        },
                        1, 6,
                        new int[]{1, 2, 4, 5, 6, 3}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[][] graph, int start, int n, int[] expected) {
        assertThat(new Solution1().solution(graph, start, n)).isEqualTo(expected);
    }
}
