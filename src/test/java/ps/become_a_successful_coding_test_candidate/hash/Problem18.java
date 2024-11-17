package ps.become_a_successful_coding_test_candidate.hash;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
두 개의 수로 특정값 만들기
n 개의 양의 정수로 이루어진 배열 arr 와 정수 target 이 주어졌을 때
이 중에서 합이 target 인 두 수가 arr 에 있는지 찾고, 있으면 true, 없으면 false 를 반환

풀이
- 브루트 포스
- 해시
-- target-i 가 contains 로 있는지 확인
 */
public class Problem18 {

    static class Solution1 {
        public boolean solution(int[] arr, int target) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    int sum = arr[i] + arr[j];
                    if (sum == target) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    static class Solution2 {
        public boolean solution(int[] arr, int target) {
            Set<Integer> set = new HashSet<>();

            for (int i : arr) {
                if (set.contains(target - i)) {
                    return true;
                }

                set.add(i);
            }

            return false;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 8}, 6, true),
                Arguments.of(new int[]{2, 3, 5, 9}, 10, false)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] arr, int target, boolean expected) {
        boolean result = new Solution1().solution(arr, target);
        assertThat(result).isEqualTo(expected);

        boolean result2 = new Solution2().solution(arr, target);
        assertThat(result2).isEqualTo(expected);
    }
}
