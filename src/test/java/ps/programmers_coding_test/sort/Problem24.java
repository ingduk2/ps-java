package ps.programmers_coding_test.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 24 두 개 뽑아서 더하기 - Level 1

풀이
- 서로 다른 인덱스의 합을 구한다
- 중복을 없애야 하므로, Set 사용
 */
public class Problem24 {

    static class Solution1 {
        public int[] solution(int[] numbers) {
            Set<Integer> result = new HashSet<>();
            for (int i = 0; i < numbers.length; i++) {
                for (int j = i + 1; j < numbers.length; j++) {
                    result.add(numbers[i] + numbers[j]);
                }
            }

            return result.stream().mapToInt(x -> x).sorted().toArray();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{2, 1, 3, 4, 1},
                        new int[]{2, 3, 4, 5, 6, 7}
                ),
                Arguments.of(
                        new int[]{5, 0, 2, 7},
                        new int[]{2, 5, 7, 9, 12}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] numbers, int[] expected) {
        assertThat(new Solution1().solution(numbers)).isEqualTo(expected);
    }
}
