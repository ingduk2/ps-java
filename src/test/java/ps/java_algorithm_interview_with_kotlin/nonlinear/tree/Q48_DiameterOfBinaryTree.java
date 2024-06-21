package ps.java_algorithm_interview_with_kotlin.nonlinear.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/description/
 * - 이진 트리의 직경
 * 1. 상태값 누적 트리 DFS
 */
public class Q48_DiameterOfBinaryTree {

    static class Solve1 {
        private int longest = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            dfs(root);
            return this.longest;
        }

        private int dfs(TreeNode node) {
            if (node == null) {
                return -1;
            }

            int left = dfs(node.left);
            int right = dfs(node.right);

            this.longest = Math.max(longest, left + right + 2);
            return Math.max(left, right) + 1;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new TreeNode(
                                1,
                                new TreeNode(2,
                                        new TreeNode(4),
                                        new TreeNode(5)),
                                new TreeNode(3)
                        ),
                        3
                ),
                Arguments.of(
                        new TreeNode(
                                1,
                                new TreeNode(2),
                                null
                        ),
                        1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(TreeNode root, int expected) {
        int result = new Solve1().diameterOfBinaryTree(root);
        assertThat(result).isEqualTo(expected);
    }
}
