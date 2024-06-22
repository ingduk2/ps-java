package ps.java_algorithm_interview_with_kotlin.nonlinear.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/longest-univalue-path/description/
 * - 가장 긴 동일 값의 경로
 * 1. 상태값 거리 계산 DFS
 */
public class Q49_LongestUniValuePath {

    static class Solve1 {
        private int result = 0;

        public int longestUnivaluePath(TreeNode root) {
            dfs(root);
            return result;
        }

        private int dfs(TreeNode node) {
            if (node == null) {
                return 0;
            }

            int left = dfs(node.left);
            int right = dfs(node.right);

            if (node.left != null && node.left.val == node.val) {
                left += 1;
            } else {
                left = 0;
            }

            if (node.right != null && node.right.val == node.val) {
                right += 1;
            } else {
                right = 0;
            }

            this.result = Math.max(this.result, left + right);
            return Math.max(left, right);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new TreeNode(
                                5,
                                new TreeNode(4,
                                        new TreeNode(1),
                                        new TreeNode(1)),
                                new TreeNode(5,
                                        null,
                                        new TreeNode(5))
                        ),
                        2
                ),
                Arguments.of(
                        new TreeNode(
                                1,
                                new TreeNode(4,
                                        new TreeNode(4),
                                        new TreeNode(4)),
                                new TreeNode(5,
                                        null,
                                        new TreeNode(5))
                        ),
                        2
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(TreeNode root, int expected) {
        int result = new Solve1().longestUnivaluePath(root);
        assertThat(result).isEqualTo(expected);
    }
}
