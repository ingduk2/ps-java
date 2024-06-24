package ps.java_algorithm_interview_with_kotlin.nonlinear.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/balanced-binary-tree/description/
 * - 균형 이진 트리
 * - 모든 노드의 서브트리 간의 높이 차이가 1이하 인 것
 */
public class Q53_BalancedBinaryTree {

    static class Solution1 {
        public boolean isBalanced(TreeNode root) {
            return dfs(root) != -1;
        }

        private int dfs(TreeNode node) {
            if (node == null) {
                return 0;
            }

            int left = dfs(node.left);
            int right = dfs(node.right);

            if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
                return -1;
            }

            return Math.max(left, right) + 1;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new TreeNode(3,
                                new TreeNode(9,
                                        null,
                                        null),
                                new TreeNode(20,
                                        new TreeNode(15),
                                        new TreeNode(7))),
                        true
                ),
                Arguments.of(
                        new TreeNode(1,
                                new TreeNode(2,
                                        new TreeNode(3,
                                                new TreeNode(4),
                                                new TreeNode(4)),
                                        new TreeNode(3)),
                                new TreeNode(2)),
                        false
                ),
                Arguments.of(
                        null,
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(TreeNode root, boolean expected) {
        boolean result = new Solution1().isBalanced(root);
        assertThat(result).isEqualTo(expected);
    }
}
