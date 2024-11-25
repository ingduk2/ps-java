package ps.become_a_successful_coding_test_candidate.set;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 31 포켓몬
- N 마리의 포켓몬 중 N/2 마리를 가져가도 좋다고 함
- 포켓몬은 종류에 따라 번호를 붙여 구분, (같은 종류는 같은 번호)
- 최대한 많은 종류의 포켓몬을 포함해서 N/2 마리를 선택
- 그때의 포켓몬의 개수

풀이
- 최대 개수는 nums.length / 2
- nums 배열을 set 에 add 해서 개수를 구한다.
- 결과 구하기
-- set 의 개수 >= 최대 개수 -> 최대 개수
-- set 의 개수 < 최대 개수 -> set 의 개수
-- set 의 개수, 최대 개수의 최소값
 */
public class Problem31 {

    static class Solution1 {
        public int solution(int[] nums) {
            Set<Integer> pocketmons = new HashSet<>();
            for (int num : nums) {
                pocketmons.add(num);
            }

            int max = nums.length / 2;

            return Math.min(max, pocketmons.size());
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int[]{3, 1, 2, 3}, 2),
                Arguments.of(new int[]{3, 3, 3, 2, 2, 4}, 3),
                Arguments.of(new int[]{3, 3, 3, 2, 2, 2}, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] nums, int expected) {
        assertThat(new Solution1().solution(nums)).isEqualTo(expected);
    }
}
