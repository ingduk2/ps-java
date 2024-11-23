package ps.become_a_successful_coding_test_candidate.set;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 30 간단한 유니온-파인드 알고리즘 구현하기
- union(x, y) : x 와 y 가 속한 두 집합을 합칩니다
- find(x) : x가 속한 집합의 대표 원소를 찾습니다

operations 배열은 수행할 연산을 의미
- [0, 1, 2] 는 노드 1과 노드 2에 대해 union 연산 수행
- [1, 1, 3] 노드 1과 3이 같은 집합에 속해 있으면 true 아니면 false

- 초기의 노드는 부모 노드를 자신의 값으로 설정했다고 가정, 여기서는 각 집합의 루트 노드를 기준으로
- 루트 노드가 작은 노드를 더 큰 노드의 자식으로 연결하는 방법을 사용
 */
public class Problem30 {

    static class Solution1 {
        private int[] parent;

        private int find(int x) {
            if (parent[x] == x) {
                return x;
            }

            parent[x] = find(parent[x]);
            return parent[x];
        }

        private void union(int x, int y) {
            int root1 = find(x);
            int root2 = find(y);

            if (root1 < root2) {
                parent[root2] = root1; // 더 큰 노드를 더 작은 노드의 자식으로 설정
            } else {
                parent[root1] = root2; // 반대로 큰 쪽이 작은 쪽의 부모가 됨
            }
        }

        private Boolean[] solution(int k, int[][] operation) {
            parent = new int[k];
            for (int i = 0; i < k; i++) {
                parent[i] = i;
            }

            List<Boolean> answer = new ArrayList<>();

            for (int[] op : operation) {
                if (op[0] == 0) {
                    union(op[1], op[2]);
                } else {
                    answer.add(find(op[1]) == find(op[2]));
                }
            }

            return answer.toArray(new Boolean[0]);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        3,
                        new int[][]{
                                new int[]{0, 0, 1},
                                new int[]{0, 1, 2},
                                new int[]{1, 1, 2},
                        },
                        new Boolean[]{true}
                ),
                Arguments.of(
                        4,
                        new int[][]{
                                new int[]{0, 0, 1},
                                new int[]{1, 1, 2},
                                new int[]{0, 1, 2},
                                new int[]{1, 0, 2}
                        },
                        new Boolean[]{false, true}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int k, int[][] operation, Boolean[] expected) {
        assertThat(new Solution1().solution(k, operation)).isEqualTo(expected);
    }
}
