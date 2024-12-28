package ps.become_a_successful_coding_test_candidate.backtracking;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 45 피로도

풀이
- 1. 순열
- 2. 백트래킹
 */
public class Problem45 {

    static class Solution1 {
        public int solution(int k, int[][] dungeons) {
            int[] nums = new int[dungeons.length];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i;
            }

            List<List<Integer>> permutation = new ArrayList<>();
            permute(nums, new ArrayList<>(), permutation);

            int answer = -1;

            for (List<Integer> list : permutation) {
                int fatigue = k;
                int clearCount = 0;
                for (int i : list) {
                    if (fatigue >= dungeons[i][0]) {
                        fatigue -= dungeons[i][1];
                        clearCount++;
                    }
                }
                answer = Math.max(answer, clearCount);
            }

            return answer;
        }

        private void permute(int[] nums, List<Integer> current, List<List<Integer>> result) {
            if (current.size() == nums.length) {
                result.add(new ArrayList<>(current));
                return;
            }

            for (int num : nums) {
                if (current.contains(num)) {
                    continue;
                }

                current.add(num);
                permute(nums, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    static class Solution2 {
        private int answer;
        private int[][] dungeons;
        private boolean[] visited;

        public int solution(int k, int[][] dungeons) {
            answer = 0;
            this.dungeons = dungeons;
            visited = new boolean[dungeons.length];

            backtrack(k, 0);
            return answer;
        }

        private void backtrack(int k, int cnt) {
            for (int i = 0; i < dungeons.length; i++) {
                if (!visited[i] && k >= dungeons[i][0]) {
                    visited[i] = true;
                    backtrack(k - dungeons[i][1], cnt + 1);
                    answer = Math.max(answer, cnt + 1);
                    visited[i] = false;
                }
            }
        }
    }

    static class Solution3 {
        private int answer;

        public int solution(int k, int[][] dungeons) {
            answer = 0;
            boolean[] visited = new boolean[dungeons.length];

            backtrack(k, dungeons, visited, 0);

            return answer;
        }

        private void backtrack(int fatigue, int[][] dungeons, boolean[] visited, int cleared) {
            for (int i = 0; i < dungeons.length; i++) {
                if (!visited[i] && fatigue >= dungeons[i][0]) {
                    visited[i] = true;
                    backtrack(fatigue - dungeons[i][1], dungeons, visited, cleared + 1);
                    answer = Math.max(answer, cleared + 1);
                    visited[i] = false;
                }
            }
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        80,
                        new int[][]{
                                {80, 20},
                                {50, 40},
                                {30, 10}
                        },
                        3
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int k, int[][] dungeons, int expected) {
        assertThat(new Solution1().solution(k, dungeons)).isEqualTo(expected);
        assertThat(new Solution2().solution(k, dungeons)).isEqualTo(expected);
        assertThat(new Solution3().solution(k, dungeons)).isEqualTo(expected);
    }
}
