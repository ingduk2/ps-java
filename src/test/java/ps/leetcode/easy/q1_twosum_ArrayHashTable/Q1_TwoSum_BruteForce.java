package ps.leetcode.easy.q1_twosum_ArrayHashTable;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Q1_TwoSum_BruteForce {

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }


    @Test
    void solution() {
        Q1_TwoSum_BruteForce q1_twoSum = new Q1_TwoSum_BruteForce();
        Assertions.
                assertThat(q1_twoSum.twoSum(new int[]{2, 7, 11, 15}, 9))
                .isEqualTo(new int[]{0, 1});

        Assertions.
                assertThat(q1_twoSum.twoSum(new int[]{3, 2, 4}, 6))
                .isEqualTo(new int[]{1, 2});

        Assertions.
                assertThat(q1_twoSum.twoSum(new int[]{3, 3}, 6))
                .isEqualTo(new int[]{0, 1});
    }
}
