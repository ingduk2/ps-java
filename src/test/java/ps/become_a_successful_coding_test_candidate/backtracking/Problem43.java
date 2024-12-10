package ps.become_a_successful_coding_test_candidate.backtracking;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 43 1부터 N 까지 숫자 중 합이 10이 되는 조합 구하기

풀이
- 백트래킹
 */
public class Problem43 {

    static class Solution1 {
        private List<List<Integer>> result;
        private int num;

        public List<List<Integer>> solution(int n) {
            result = new ArrayList<>();
            num = n;

            backtrack(0, new ArrayList<>(), 1);
            return result;
        }

        private void backtrack(int sum, List<Integer> selectNums, int start) {
            if (sum == 10) {
                result.add(selectNums);
                return;
            }

            for (int i = start; i <= num; i++) {
                if (sum + i <= 10) {
                    List<Integer> list = new ArrayList<>(selectNums);
                    list.add(i);
                    backtrack(sum + i, list, i + 1);
                }
            }
        }
    }

    static class Solution2 {

        public List<List<Integer>> solution(int n) {
            List<List<Integer>> result = new ArrayList<>();

            backtrack(1, n, 10, new ArrayList<>(), result);
            return result;
        }

        private void backtrack(int start, int n, int targetSum, List<Integer> current, List<List<Integer>> result) {
            if (targetSum == 0) {
                result.add(new ArrayList<>(current));
                return;
            }

            if (targetSum < 0) {
                return;
            }

            for (int i = start; i <= n; i++) {
                current.add(i);
                backtrack(i + 1, n, targetSum - i, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    static class Solution3 {

        public List<List<Integer>> solution(int n) {
            List<List<Integer>> result = new ArrayList<>();

            backtrack(1, n, 0,10, new ArrayList<>(), result);
            return result;
        }

        private void backtrack(int start, int n, int currentSum, int targetSum, List<Integer> current, List<List<Integer>> result) {
            if (currentSum == targetSum) {
                result.add(new ArrayList<>(current));
                return;
            }

            for (int i = start; i <= n; i++) {
                if (currentSum + i > targetSum) {
                    continue;
                }

                current.add(i);
                backtrack(i + 1, n, currentSum + i, targetSum, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        5,
                        List.of(
                                List.of(1, 2, 3, 4),
                                List.of(1, 4, 5),
                                List.of(2, 3, 5)
                        )
                ),
                Arguments.of(
                        2,
                        List.of()
                ),
                Arguments.of(
                        7,
                        List.of(
                                List.of(1, 2, 3, 4),
                                List.of(1, 2, 7),
                                List.of(1, 3, 6),
                                List.of(1, 4, 5),
                                List.of(2, 3, 5),
                                List.of(3, 7),
                                List.of(4, 6)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, List<Integer> expected) {
        assertThat(new Solution1().solution(n)).isEqualTo(expected);
        assertThat(new Solution2().solution(n)).isEqualTo(expected);
        assertThat(new Solution3().solution(n)).isEqualTo(expected);
    }
}
