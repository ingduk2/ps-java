package ps.java_algorithm_interview_with_kotlin.nonlinear.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 * - 전위, 중위 순회 결과로 이진 트리 구축
 *
 * 1. 전위 순회 결과로 중위 순회 분할 정복
 * - 전위 : 1 2 4 5 3 6 7 9 8
 * - 중위 : 4 2 5 1 7 9 6 8 3
 * - 전위 첫 번째는 중위 순회 왼쪽, 오른쪽으로 분할하는 역할
 * 2. 참조형으로 구현
 */
public class Q59_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    static class Solution1 {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return dfs(0, 0, inorder.length - 1, preorder, inorder);
        }

        private TreeNode dfs(
                int preIndex,
                int inStart,
                int inEnd,
                int[] preorder,
                int[] inorder
        ) {
            if (preIndex > preorder.length - 1 || inStart > inEnd) {
                return null;
            }

            // 전위 순회 값이 중위 순회에서는 몇 번째 인덱스인지 추출
            int inIndex = 0;
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == preorder[preIndex]) {
                    inIndex = i;
                }
            }

            // 해당 인덱스는 중위 순회를 분할하는 노드로 지정
            TreeNode treeNode = new TreeNode(inorder[inIndex]);
            // 전위 순회 다음 결과를 보도록 인덱스 + 1
            preIndex++;
            // 왼쪽 자식 노드부터 진행
            treeNode.left = dfs(preIndex, inStart, inIndex - 1, preorder, inorder);
            // 나머지 값으로 오른쪽 자식 노드 진행
            treeNode.right = dfs(preIndex + inIndex - inStart, inIndex + 1, inEnd, preorder, inorder);

            return treeNode;
        }
    }

    static class Solution2 {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            List<Integer> preOrder = Arrays.stream(preorder)
                    .boxed().toList();
            List<Integer> inOrder = Arrays.stream(inorder)
                    .boxed().toList();

            return dfs(preOrder, inOrder);
        }

        private TreeNode dfs(List<Integer> preOrder, List<Integer> inOrder) {
            if (inOrder.isEmpty()) {
                return null;
            }

            // 전위 순회 값이 중위 순회에서는 몇 번째 인덱스인지 추출
            int inIndex = inOrder.indexOf(preOrder.get(0));
            // 해당 인덱스는 중위 순회를 분할하는 노드로 지정
            TreeNode treeNode = new TreeNode(inOrder.get(inIndex));
            // 왼쪽 자식 노드부터 진행
            treeNode.left = dfs(
                    preOrder.subList(1, inIndex + 1),
                    inOrder.subList(0, inIndex)
            );
            // 나머지 값으로 오른쪽 자식 노드 진행
            treeNode.right = dfs(
                    preOrder.subList(inIndex + 1, preOrder.size()),
                    inOrder.subList(inIndex + 1, inOrder.size())
            );

            return treeNode;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{3, 9, 20, 15, 7},
                        new int[]{9, 3, 15, 20, 7},
                        new TreeNode(3,
                                new TreeNode(9),
                                new TreeNode(20,
                                        new TreeNode(15),
                                        new TreeNode(7)))
                ),
                Arguments.of(
                        new int[]{-1},
                        new int[]{-1},
                        new TreeNode(-1)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] preorder, int[] inorder, TreeNode expected) {
        TreeNode result = new Solution1().buildTree(preorder, inorder);
        assertThat(result.toList()).isEqualTo(expected.toList());

        TreeNode result2 = new Solution2().buildTree(preorder, inorder);
        assertThat(result2.toList()).isEqualTo(expected.toList());
    }
}
