package ps.java_algorithm_interview_with_kotlin.nonlinear.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/range-sum-of-bst/description/
 * - 이진 탐색 트리 (BST) 합의 범위
 *
 * 1. 재귀 구조 DFS 로 브루트 포스 탐색
 * 2. DFS 가지치기로 필요한 노드 탐색
 * 3. 반복 구조 DFS 로 필요한 노드 탐색
 * 4. 반복 구조 BFS 로 필요한 노드 탐색
 */
public class Q57_RangeSumOfBST {

    static class Solution1 {
        public int rangeSumBST(TreeNode root, int low, int high) {
            if (root == null) {
                return 0;
            }

            int result = 0;
            if (low <= root.val && root.val <= high) {
                result = root.val;
            }

            result += rangeSumBST(root.left, low, high);
            result += rangeSumBST(root.right, low, high);
            return result;
        }
    }

    static class Solution2 {
        public int rangeSumBST(TreeNode root, int low, int high) {
            if (root == null) {
                return 0;
            }

            if (root.val > high) {
                return rangeSumBST(root.left, low, high);
            }

            if (root.val < low) {
                return rangeSumBST(root.right, low, high);
            }

            return root.val +
                    rangeSumBST(root.left, low, high) +
                    rangeSumBST(root.right, low, high);
        }
    }

    static class Solution3 {
        public int rangeSumBST(TreeNode root, int low, int high) {
            Deque<TreeNode> stack = new ArrayDeque<>();
            stack.push(root);

            int result = 0;

            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();

                if (cur.val > low) {
                    if (cur.left != null) {
                        stack.push(cur.left);
                    }
                }
                if (cur.val < high) {
                    if (cur.right != null) {
                        stack.push(cur.right);
                    }
                }
                if (low <= cur.val && cur.val <= high) {
                    result += cur.val;
                }
            }

            return result;
        }
    }

    static class Solution4 {
        public int rangeSumBST(TreeNode root, int low, int high) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            int result = 0;

            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();

                if (cur.val > low) {
                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }
                }
                if (cur.val < high) {
                    if (cur.right != null) {
                        queue.offer(cur.right);
                    }
                }
                if (low <= cur.val && cur.val <= high) {
                    result += cur.val;
                }
            }

            return result;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new TreeNode(10,
                                new TreeNode(5,
                                        new TreeNode(3),
                                        new TreeNode(7)),
                                new TreeNode(15,
                                        null,
                                        new TreeNode(18))),
                        7, 15,
                        32
                ),
                Arguments.of(
                        new TreeNode(10,
                                new TreeNode(5,
                                        new TreeNode(3,
                                                new TreeNode(1),
                                                null),
                                        new TreeNode(7,
                                                new TreeNode(6),
                                                null)),
                                new TreeNode(15,
                                        new TreeNode(13),
                                        new TreeNode(18))),
                        6, 10,
                        23
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(TreeNode root, int low, int high, int expected) {
        int result = new Solution1().rangeSumBST(root, low, high);
        assertThat(result).isEqualTo(expected);

        int result2 = new Solution2().rangeSumBST(root, low, high);
        assertThat(result2).isEqualTo(expected);

        int result3 = new Solution3().rangeSumBST(root, low, high);
        assertThat(result3).isEqualTo(expected);

        int result4 = new Solution4().rangeSumBST(root, low, high);
        assertThat(result4).isEqualTo(expected);
    }
}
