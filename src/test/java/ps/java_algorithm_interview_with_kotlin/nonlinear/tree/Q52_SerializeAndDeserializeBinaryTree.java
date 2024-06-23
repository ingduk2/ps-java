package ps.java_algorithm_interview_with_kotlin.nonlinear.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
 * - 이진 트리 직렬화 & 역직렬화
 */
public class Q52_SerializeAndDeserializeBinaryTree {

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "";

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            List<String> tree = new LinkedList<>();
            tree.add("#");
            tree.add(String.valueOf(root.val));

            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();

                if (cur.left != null) {
                    queue.offer(cur.left);
                    tree.add(String.valueOf(cur.left.val));
                } else {
                    tree.add("#");
                }

                if (cur.right != null) {
                    queue.offer(cur.right);
                    tree.add(String.valueOf(cur.right.val));
                } else {
                    tree.add("#");
                }
            }

            return String.join(",", tree);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (Objects.equals(data, "")) return null;

            String[] nodes = data.split(",");
            TreeNode root = new TreeNode(Integer.parseInt(nodes[1]));

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            int index = 2;
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                if (!nodes[index].equals("#")) {
                    cur.left = new TreeNode(Integer.parseInt(nodes[index]));
                    queue.offer(cur.left);
                }

                index += 1;

                if (!nodes[index].equals("#")) {
                    cur.right = new TreeNode(Integer.parseInt(nodes[index]));
                    queue.offer(cur.right);
                }

                index += 1;
            }

            return root;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new TreeNode(1,
                                new TreeNode(2,
                                        null,
                                        null),
                                new TreeNode(3,
                                        new TreeNode(4),
                                        new TreeNode(5))),
                        new TreeNode(1,
                                new TreeNode(2,
                                        null,
                                        null),
                                new TreeNode(3,
                                        new TreeNode(4),
                                        new TreeNode(5)))
                ),
                Arguments.of(
                        null,
                        null
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(TreeNode root, TreeNode expected) {
        Codec ser = new Codec();
        Codec deser = new Codec();
        String serialize = ser.serialize(root);
        TreeNode result = deser.deserialize(serialize);

        if (result == null) {
            assertThat(expected).isNull();
        } else {
            assertThat(result.toList()).isEqualTo(expected.toList());
        }
    }
}
