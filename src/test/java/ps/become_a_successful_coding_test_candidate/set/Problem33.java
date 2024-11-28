package ps.become_a_successful_coding_test_candidate.set;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 33 섬 연결하기

풀이
- 각 섬 사이의 다리를 건설하는 비용을 오름차순으로 정렬
- 비용이 작은 다리부터 선택해 섬을 연결
- N 개의 섬을 연결하려면 N-1 의 다리가 필요하므로 N-1 개의 다리가 선택될 때까지 위 두 과정을 반복
- 비용을 최소화하기 위해 다리를 추가할 때 사이클을 형성하지 않도록 함
 */
public class Problem33 {

    static class Solution1 {
        private int[] parent;

        private int find(int x) {
            if (parent[x] == x) {
                return x;
            }

            return parent[x] = find(parent[x]);
        }

        private void union(int x, int y) {
            int root1 = find(x);
            int root2 = find(y);
            parent[root2] = root1;
        }

        public int solution(int n, int[][] costs) {
            Arrays.sort(costs, (o1, o2) -> Integer.compare(o1[2], o2[2]));

            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            int answer = 0;
            int edges = 0;

            for (int[] edge : costs) {
                if (edges == n - 1) {
                    break;
                }

                if (find(edge[0]) != find(edge[1])) {
                    union(edge[0], edge[1]);
                    answer += edge[2];
                    edges++;
                }
            }

            return answer;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        4,
                        new int[][]{
                                {0, 1, 1},
                                {0, 2, 2},
                                {1, 2, 5},
                                {1, 3, 1},
                                {2, 3, 8}
                        },
                        4
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, int[][] costs, int expected) {
        assertThat(new Solution1().solution(n, costs)).isEqualTo(expected);
    }
}
