package ps.become_a_successful_coding_test_candidate.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 08 올바른 괄호

"()()" 또는 "(())()" 는 올바른 괄호입니다.
")()(" 또는 "(()(" 는 올바르지 않은 괄호입니다.

풀이
- stack 사용
 */
public class Problem8 {

    static class Solution1 {
        boolean solution(String s) {
            Deque<Character> stack = new ArrayDeque<>();

            char[] charArray = s.toCharArray();
            for (char c : charArray) {
                if (c == '(') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) {
                        return false;
                    }

                    stack.pop();
                }
            }

            return stack.isEmpty();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("()()", true),
                Arguments.of("(())()", true),
                Arguments.of(")()(", false),
                Arguments.of("(()(", false),
                Arguments.of("(", false),
                Arguments.of(")", false),
                Arguments.of("())", false),
                Arguments.of("()(", false)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String s, boolean expected) {
        boolean result = new Solution1().solution(s);
        assertThat(result).isEqualTo(expected);
    }
}
