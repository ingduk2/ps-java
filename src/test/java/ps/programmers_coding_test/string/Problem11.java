package ps.programmers_coding_test.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 11 문자열 내 p와 y의 개수 - Level 1

풀이
- 1. p, y 모두 하나도 없는 경우 true
- 2. p 의 개수와 y 의 개수가 같으면 true, 다르면 false
 */
public class Problem11 {

    static class Solution1 {
        boolean solution(String s) {
            int pCount = 0;
            int yCount = 0;

            for (char c : s.toLowerCase().toCharArray()) {
                if (c == 'p') pCount++;
                if (c == 'y') yCount++;
            }

            if (pCount == 0 && yCount == 0) {
                return true;
            }

            return pCount == yCount;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("pPoooyY", true),
                Arguments.of("Pyy", false),
                Arguments.of("abcde", true)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String s, boolean expected) {
        assertThat(new Solution1().solution(s)).isEqualTo(expected);
    }
}
