package ps.java_algorithm_interview_with_kotlin.nonlinear.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static ps.java_algorithm_interview_with_kotlin.test.Assertions.assertList;

/**
 * https://leetcode.com/problems/permutations/description/
 * - 순열
 *
 */
public class Q37_Permutations {

    static class ListSolve {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> list = Arrays.stream(nums).boxed().toList();
            dfs(result, new ArrayList<>(), list);

            return result;
        }

        private void dfs(
                List<List<Integer>> result,
                List<Integer> prevElements,
                List<Integer> elements
        ) {
            if (elements.isEmpty()) {
                result.add(prevElements.stream().toList());
                return;
            }

            for (Integer e : elements) {
                // 현재 엘리먼트 제외 하고
                List<Integer> nextElement = new ArrayList<>(elements);
                nextElement.remove(e);

                prevElements.add(e);
                dfs(result, prevElements, nextElement);
                prevElements.remove(prevElements.size() - 1);
            }
        }
    }

    static class ArraySolve {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            dfs(result, new ArrayList<>(), nums);

            return result;
        }

        private void dfs(
                List<List<Integer>> result,
                List<Integer> tempList,
                int[] nums
        ) {
            if (tempList.size() == nums.length) {
                result.add(tempList.stream().toList());
                return;
            }

            for (int num : nums) {
                if (tempList.contains(num)) continue;
                tempList.add(num);
                dfs(result, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }


    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 2, 3},
                        List.of(
                                List.of(1, 2, 3),
                                List.of(1, 3, 2),
                                List.of(2, 1, 3),
                                List.of(2, 3, 1),
                                List.of(3, 1, 2),
                                List.of(3, 2, 1)
                        )
                ),
                Arguments.of(
                        new int[]{0, 1},
                        List.of(
                                List.of(0, 1),
                                List.of(1, 0)
                        )
                ),
                Arguments.of(
                        new int[]{1},
                        List.of(
                                List.of(1)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] nums, List<List<Integer>> expected) {
        List<List<Integer>> result = new ListSolve().permute(nums);
        assertList(result, expected);

        List<List<Integer>> result2 = new ArraySolve().permute(nums);
        assertList(result2, expected);
    }
}
