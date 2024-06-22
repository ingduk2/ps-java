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
 * https://leetcode.com/problems/invert-binary-tree/description/
 * - 이진 트리 반전
 *
 * 1. 신규 노드 생성 재귀 구조 DFS
 * 2. 재귀 구조 DFS
 * 3. 재귀 구조 DFS 후위 순회
 * 4. 반복 구조 DFS
 * 5. 반복 구조 BFS
 */
public class Q50_InvertBinaryTree {

    static class Solve1 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }

            TreeNode newNode = new TreeNode(root.val);
            newNode.left = invertTree(root.right);
            newNode.right = invertTree(root.left);
            return newNode;
        }
    }

    static class Solve2 {
        public TreeNode invertTree(TreeNode root) {
            if (root != null) {
                TreeNode temp = root.left;
                root.left = root.right;
                root.right = temp;

                invertTree(root.left);
                invertTree(root.right);
            }

            return root;
        }
    }

    static class Solve3 {
        public TreeNode invertTree(TreeNode root) {
            if (root != null) {
                invertTree(root.left);
                invertTree(root.right);

                TreeNode temp = root.left;
                root.left = root.right;
                root.right = temp;
            }

            return root;
        }
    }

    static class Solve4 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }

            Deque<TreeNode> stack = new ArrayDeque<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;

                if (node.left != null) {
                    stack.push(node.left);
                }
                if (node.right != null) {
                    stack.push(node.right);
                }
            }

            return root;
        }
    }

    static class Solve5 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();

                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            return root;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new TreeNode(
                                4,
                                new TreeNode(2,
                                        new TreeNode(1),
                                        new TreeNode(3)),
                                new TreeNode(7,
                                        new TreeNode(6),
                                        new TreeNode(9))
                        ),
                        new TreeNode(
                                4,
                                new TreeNode(7,
                                        new TreeNode(9),
                                        new TreeNode(6)),
                                new TreeNode(2,
                                        new TreeNode(3),
                                        new TreeNode(1))
                        )
                ),
                Arguments.of(
                        new TreeNode(
                                2,
                                new TreeNode(1),
                                new TreeNode(3)
                        ),
                        new TreeNode(
                                2,
                                new TreeNode(3),
                                new TreeNode(1)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(TreeNode root, TreeNode expected) {
        TreeNode result = new Solve1().invertTree(root);
        assertThat(result.toList()).isEqualTo(expected.toList());
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test2(TreeNode root, TreeNode expected) {
        TreeNode result = new Solve2().invertTree(root);
        assertThat(result.toList()).isEqualTo(expected.toList());
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test3(TreeNode root, TreeNode expected) {
        TreeNode result = new Solve3().invertTree(root);
        assertThat(result.toList()).isEqualTo(expected.toList());
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test4(TreeNode root, TreeNode expected) {
        TreeNode result = new Solve4().invertTree(root);
        assertThat(result.toList()).isEqualTo(expected.toList());
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test5(TreeNode root, TreeNode expected) {
        TreeNode result = new Solve5().invertTree(root);
        assertThat(result.toList()).isEqualTo(expected.toList());
    }
}
