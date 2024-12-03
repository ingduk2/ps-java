package ps.become_a_successful_coding_test_candidate.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 38 네트워크

풀이
- 모든 경로 - dfs
- dfs 를 몇 번 탐색했는지 보면 된다
 */
public class Problem38 {

    static class Solution1 {
        private List<Integer>[] adjList;
        private boolean[] visited;

        public int solution(int n, int[][] computers) {
            adjList = new ArrayList[n];
            for (int i = 0; i < adjList.length; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j && computers[i][j] == 1) {
                        adjList[i].add(j);
                    }
                }
            }

            int answer = 0;
            visited = new boolean[n];

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dfs(i);
                    answer++;
                }
            }

            return answer;
        }

        private void dfs(int now) {
            visited[now] = true;

            for (int next : adjList[now]) {
                if (!visited[next]) {
                    dfs(next);
                }
            }
        }
    }

    static class Solution2 {
        private int[][] computer;
        private boolean[] visited;

        public int solution(int n, int[][] computers) {
            int answer = 0;
            computer = computers;
            visited = new boolean[n];

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dfs(i);
                    answer++;
                }
            }

            return answer;
        }

        private void dfs(int now) {
            visited[now] = true;

            for (int i = 0; i < computer[now].length; i++) {
                if (computer[now][i] == 1 && !visited[i]) {
                    dfs(i);
                }
            }
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        3,
                        new int[][]{
                                {1, 1, 0},
                                {1, 1, 0},
                                {0, 0, 1},
                        },
                        2
                ),
                Arguments.of(
                        3,
                        new int[][]{
                                {1, 1, 0},
                                {1, 1, 1},
                                {0, 1, 1},
                        },
                        1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, int[][] computers, int expected) {
        assertThat(new Solution1().solution(n, computers)).isEqualTo(expected);
        assertThat(new Solution2().solution(n, computers)).isEqualTo(expected);
    }
}
