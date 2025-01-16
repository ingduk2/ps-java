package ps.programmers_coding_test.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 10 이진 변환 반복하기 - Level 2

풀이
- 1. 모든 0을 제거 (0의 개수 카운팅)
- 1-1. 제거한 후 길이를 2진법으로 표현한 문자열로 교체
- 2. s 가 "1" 이 될 때까지 계속해서 이진 변환
- 3. 변환의 횟수, 제거된 모든 0의 개수를 배열에
 */
public class Problem10 {

    static class Solution1 {
        public int[] solution(String s) {
            int conversionCount = 0;
            int removedZeroCount = 0;

            while (!s.equals("1")) {
                int zeroCount = getZeroCount(s);
                int removedLength = s.length() - zeroCount;
                s = Integer.toString(removedLength, 2);

                conversionCount++;
                removedZeroCount += zeroCount;
            }

            return new int[]{conversionCount, removedZeroCount};
        }

        private int getZeroCount(String s) {
            int count = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    count++;
                }
            }

            return count;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("110010101001", new int[]{3, 8}),
                Arguments.of("01110", new int[]{3, 3}),
                Arguments.of("1111111", new int[]{4, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String s, int[] expected) {
        assertThat(new Solution1().solution(s)).isEqualTo(expected);
    }
}
