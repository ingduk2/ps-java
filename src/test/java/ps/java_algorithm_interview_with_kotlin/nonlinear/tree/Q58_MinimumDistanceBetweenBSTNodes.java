package ps.java_algorithm_interview_with_kotlin.nonlinear.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/
 * - 이진 탐색 트리 (BST) 노드 간 최솟값
 *
 * 1. 재귀 구조로 중위 순회
 * 2. 반복 구조로 중위 순회
 */
public class Q58_MinimumDistanceBetweenBSTNodes {

    static class Solution1 {

        private int prev = Integer.MIN_VALUE + 10000;
        private int minDiff = Integer.MAX_VALUE;

        public int minDiffInBST(TreeNode root) {
            if (root.left != null) {
                minDiffInBST(root.left);
            }

            minDiff = Math.min(minDiff, root.val - prev);
            prev = root.val;

            if (root.right != null) {
                minDiffInBST(root.right);
            }

            return minDiff;
        }
    }

    static class Solution2 {

        public int minDiffInBST(TreeNode root) {
            int prev = Integer.MIN_VALUE + 10000;
            int minDiff = Integer.MAX_VALUE;

            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode node = root;

            while (!stack.isEmpty() || node != null) {
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }

                node = stack.pop();

                minDiff = Math.min(minDiff, node.val - prev);
                prev = node.val;

                node = node.right;
            }

            return minDiff;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new TreeNode(4,
                                new TreeNode(2,
                                        new TreeNode(1),
                                        new TreeNode(3)),
                                new TreeNode(6)),
                        1
                ),
                Arguments.of(
                        new TreeNode(1,
                                new TreeNode(0),
                                new TreeNode(48,
                                        new TreeNode(12),
                                        new TreeNode(49))),
                        1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(TreeNode root, int expected) {
        int result = new Solution1().minDiffInBST(root);
        assertThat(result).isEqualTo(expected);

        int result2 = new Solution2().minDiffInBST(root);
        assertThat(result2).isEqualTo(expected);
    }
}
