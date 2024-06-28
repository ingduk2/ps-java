package ps.java_algorithm_interview_with_kotlin.nonlinear.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/description/
 * - 이진 탐색 트리(BST) 를 더 큰 수 합계 트리로
 *
 * 1. 중위 순회로 노드 값 누적
 * - BST 의 우측 자식 노드는 항상 부모 노드보다 크다
 * - 자기 자신을 포함한 우측 자식 노드의 합을 구하면 된다
 * - 오른쪽 -> 부모 -> 왼쪽
 */
public class Q56_BinarySearchTreeToGreaterSumTree {

    static class Solution1 {
        private int val = 0;
        public TreeNode bstToGst(TreeNode root) {
            if (root != null) {
                bstToGst(root.right);
                val += root.val;
                root.val = val;
                bstToGst(root.left);
            }

            return root;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new TreeNode(4,
                                new TreeNode(1,
                                        new TreeNode(0),
                                        new TreeNode(2,
                                                null,
                                                new TreeNode(3))),
                                new TreeNode(6,
                                        new TreeNode(5),
                                        new TreeNode(7,
                                                null,
                                                new TreeNode(8)))),
                        new TreeNode(30,
                                new TreeNode(36,
                                        new TreeNode(36),
                                        new TreeNode(35,
                                                null,
                                                new TreeNode(33))),
                                new TreeNode(21,
                                        new TreeNode(26),
                                        new TreeNode(15,
                                                null,
                                                new TreeNode(8))))
                ),
                Arguments.of(
                        new TreeNode(0,
                                null,
                                new TreeNode(1)),
                        new TreeNode(1,
                                null,
                                new TreeNode(1))
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(TreeNode root, TreeNode expected) {
        TreeNode result = new Solution1().bstToGst(root);
        assertThat(result.toList()).isEqualTo(expected.toList());
    }
}
