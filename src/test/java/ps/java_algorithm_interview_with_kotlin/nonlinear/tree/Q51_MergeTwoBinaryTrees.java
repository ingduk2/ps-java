package ps.java_algorithm_interview_with_kotlin.nonlinear.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/merge-two-binary-trees/description/
 * - 두 이진 트리 병합
 *
 * 1. 재귀 DFS
 */
public class Q51_MergeTwoBinaryTrees {

    static class Solution1 {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            if (root1 == null) return root2;
            if (root2 == null) return root1;

            TreeNode node = new TreeNode(root1.val + root2.val);
            node.left = mergeTrees(root1.left, root2.left);
            node.right = mergeTrees(root1.right, root2.right);

            return node;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new TreeNode(1,
                                new TreeNode(3,
                                        new TreeNode(5),
                                        null),
                                new TreeNode(2)),
                        new TreeNode(2,
                                new TreeNode(1,
                                        null,
                                        new TreeNode(4)),
                                new TreeNode(3,
                                        null,
                                        new TreeNode(7))),
                        new TreeNode(3,
                                new TreeNode(4,
                                        new TreeNode(5),
                                        new TreeNode(4)),
                                new TreeNode(5,
                                        null,
                                        new TreeNode(7)))
                ),
                Arguments.of(
                        new TreeNode(1),
                        new TreeNode(1,
                                new TreeNode(2),
                                null),
                        new TreeNode(2,
                                new TreeNode(2),
                                null)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(TreeNode root1, TreeNode root2, TreeNode expected) {
        TreeNode result = new Solution1().mergeTrees(root1, root2);
        assertThat(result.toList()).isEqualTo(expected.toList());
    }
}
