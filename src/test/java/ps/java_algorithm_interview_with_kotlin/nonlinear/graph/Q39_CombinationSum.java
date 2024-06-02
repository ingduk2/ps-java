package ps.java_algorithm_interview_with_kotlin.nonlinear.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static ps.java_algorithm_interview_with_kotlin.test.Assertions.assertList;

/**
 * https://leetcode.com/problems/combination-sum/description/
 * - 조합의 합 (중복 조합)
 *
 */
public class Q39_CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        dfs(results, candidates, target, 0, new ArrayList<>());

        return results;
    }

    private void dfs(
            List<List<Integer>> results,
            int[] candidates,
            int target,
            int index,
            List<Integer> path
    ) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            results.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(results, candidates, target - candidates[i], i, path);
            path.remove(path.size() - 1);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{2, 3, 6, 7}, 7,
                        List.of(
                                List.of(2, 2, 3),
                                List.of(7)
                        )
                ),
                Arguments.of(
                        new int[]{2, 3, 5}, 8,
                        List.of(
                                List.of(2, 2, 2, 2),
                                List.of(2, 3, 3),
                                List.of(3, 5)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] candidates, int target, List<List<Integer>> expected) {
        List<List<Integer>> result = new Q39_CombinationSum().combinationSum(candidates, target);
        assertList(result, expected);
    }
}
