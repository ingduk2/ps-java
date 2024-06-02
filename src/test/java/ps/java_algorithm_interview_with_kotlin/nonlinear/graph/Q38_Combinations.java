package ps.java_algorithm_interview_with_kotlin.nonlinear.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static ps.java_algorithm_interview_with_kotlin.test.Assertions.assertList;

/**
 * https://leetcode.com/problems/combinations/
 * - 조합
 */
public class Q38_Combinations {
    static class Solve1 {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> result = new ArrayList<>();
            dfs(result, new ArrayList<>(), n, 1, k);

            return result;
        }

        private void dfs(
                List<List<Integer>> result,
                List<Integer> elements,
                int n, int start, int k
        ) {
            if (k == 0) {
                result.add(elements.stream().toList());
                return;
            }

            for (int i = start; i <= n; i++) {
                elements.add(i);
                dfs(result, elements, n, i + 1, k - 1);
                elements.remove(elements.size() - 1);
            }
        }
    }

    static class Solve2 {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> result = new ArrayList<>();
            int[] nums = IntStream.rangeClosed(1, n).toArray();
            dfs(result, new ArrayList<>(), nums, 0, k);

            return result;
        }

        private void dfs(
                List<List<Integer>> result,
                List<Integer> current,
                int[] nums,
                int start,
                int k
        ) {
            if (current.size() == k) {
                result.add(current.stream().toList());
                return;
            }

            for (int i = start; i < nums.length; i++) {
                current.add(nums[i]);
                dfs(result, current, nums, i + 1, k);
                current.remove(current.size() - 1);
            }
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        4, 2,
                        List.of(
                                List.of(1, 2),
                                List.of(1, 3),
                                List.of(1, 4),
                                List.of(2, 3),
                                List.of(2, 4),
                                List.of(3, 4)
                        )
                ),
                Arguments.of(
                        1, 1,
                        List.of(
                                List.of(1)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, int k, List<List<Integer>> expected) {
        List<List<Integer>> result = new Solve1().combine(n, k);
        assertList(result, expected);

        List<List<Integer>> result2 = new Solve2().combine(n, k);
        assertList(result2, expected);
    }
}
