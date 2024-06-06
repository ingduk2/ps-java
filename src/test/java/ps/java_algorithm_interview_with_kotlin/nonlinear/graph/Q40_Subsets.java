package ps.java_algorithm_interview_with_kotlin.nonlinear.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static ps.java_algorithm_interview_with_kotlin.test.Assertions.assertList;

/**
 * https://leetcode.com/problems/subsets/
 * - subset 구하기
 * - 중복은 포함하지 않는다
 * - 순서는 상관 없다
 *
 * 1. 트리의 모든 DFS 결과
 * - 조건 k 가 필요 없다
 */
public class Q40_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        dfs(result, new ArrayList<>(), nums, 0);

        return result;
    }

    private void dfs(
            List<List<Integer>> result,
            List<Integer> current,
            int[] nums,
            int start
    ) {
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            dfs(result, current, nums, i + 1);
            current.remove(current.size() - 1);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 2, 3},
                        List.of(
                                List.of(),
                                List.of(1),
                                List.of(2),
                                List.of(1, 2),
                                List.of(3),
                                List.of(1, 3),
                                List.of(2, 3),
                                List.of(1, 2, 3)
                        )
                ),
                Arguments.of(
                        new int[]{0},
                        List.of(
                                List.of(),
                                List.of(0)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] nums, List<List<Integer>> expected) {
        List<List<Integer>> result = new Q40_Subsets().subsets(nums);
        assertList(result, expected);
    }
}
