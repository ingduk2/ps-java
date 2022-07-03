package ps.leetcode.easy.q1_twosum_ArrayHashTable;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Q1_TwoSum_TwoPassHashTable {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] {i, map.get(complement)};
            }
        }
        return new int[]{};
    }

    @Test
    void solution() {
        Q1_TwoSum_TwoPassHashTable q1_twoSum = new Q1_TwoSum_TwoPassHashTable();
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
