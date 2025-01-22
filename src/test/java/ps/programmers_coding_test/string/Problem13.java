package ps.programmers_coding_test.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 13 문자열 다루기 기본 - Level 1

풀이
- 길이가 4 or 6 인지 확인한다
- 숫자로만 구성되어있는지 확인한다
 */
public class Problem13 {

    static class Solution1 {
        public boolean solution(String s) {
            return checkLength(s) && checkContainsNumber(s);
        }

        private boolean checkLength(String s) {
            return s.length() == 4 || s.length() == 6;
        }

        private boolean checkContainsNumber(String s) {
            for (char c : s.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }

            return true;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("a234", false),
                Arguments.of("1234", true)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String s, boolean expected) {
        assertThat(new Solution1().solution(s)).isEqualTo(expected);
    }
}
