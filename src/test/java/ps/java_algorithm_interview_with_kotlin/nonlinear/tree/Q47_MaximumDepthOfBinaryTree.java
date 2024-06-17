package ps.java_algorithm_interview_with_kotlin.nonlinear.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 * - 이진 트리의 최대 깊이
 * 1. 반복 구조 BFS
 * 2. 재귀 구조 DFS
 */
public class Q47_MaximumDepthOfBinaryTree {

    static class Solve1 {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            int depth = 0;
            while (!queue.isEmpty()) {
                depth += 1;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    if (cur.left != null) {
                        queue.add(cur.left);
                    }
                    if (cur.right != null) {
                        queue.add(cur.right);
                    }
                }
            }

            return depth;
        }
    }

    static class Solve2 {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);

            return Math.max(left, right) + 1;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new TreeNode(
                                3,
                                new TreeNode(9,
                                        null,
                                        null),
                                new TreeNode(20,
                                        new TreeNode(15),
                                        new TreeNode(7))
                        ),
                        3
                ),
                Arguments.of(
                        new TreeNode(
                                1,
                                null,
                                new TreeNode(2)
                        ),
                        2
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(TreeNode root, int expected) {
        int result = new Solve1().maxDepth(root);
        assertThat(result).isEqualTo(expected);

        int result2 = new Solve2().maxDepth(root);
        assertThat(result2).isEqualTo(expected);
    }
}
