package ps.java_algorithm_interview_with_kotlin.nonlinear.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
 * - 정렬된 배열의 이진 탐색 트리 변환
 * - 균형 이진 탐색 트리로 변환
 *
 * 1. 이진 검색 결과로 트리 구성
 * - [-10, -7, -3, 0, 5, 7, 9]
 * -               0
 * -       -7            7
 * -  -10      -3     5     9
 */
public class Q55_ConvertSortedArrayToBinarySearchTree {

    static class Solution1 {
        public TreeNode sortedArrayToBST(int[] nums) {
            if (nums.length == 0) {
                return null;
            }

            return construct(nums, 0, nums.length - 1);
        }

        private TreeNode construct(int[] nums, int low, int high) {
            if (low > high) {
                return null;
            }

            int mid = low + (high - low) / 2;
            TreeNode treeNode = new TreeNode(nums[mid]);
            treeNode.left = construct(nums, low, mid -1);
            treeNode.right = construct(nums, mid + 1, high);

            return treeNode;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{-10, -3, 0, 5, 9},
                        List.of(
                                new TreeNode(0,
                                        new TreeNode(-3,
                                                new TreeNode(-10),
                                                null),
                                        new TreeNode(9,
                                                new TreeNode(5),
                                                null)),
                                new TreeNode(0,
                                        new TreeNode(-10,
                                                null,
                                                new TreeNode(-3)),
                                        new TreeNode(5,
                                                null,
                                                new TreeNode(9)))
                        )
                ),
                Arguments.of(
                        new int[]{1, 3},
                        List.of(
                                new TreeNode(3,
                                        new TreeNode(1),
                                        null),
                                new TreeNode(1,
                                        null,
                                        new TreeNode(3))
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] nums, List<TreeNode> expected) {
        TreeNode result = new Solution1().sortedArrayToBST(nums);

        boolean contains = expected.stream()
                .anyMatch(it -> it.toList().equals(result.toList()));
        assertThat(contains).isTrue();
    }
}
