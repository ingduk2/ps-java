package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/valid-parentheses/description/
 * - () {} [] 짝이 맞는지 확인
 *
 * 1.
 * - 열린 괄호 일떄 stack 에 push
 * - 닫힌 괄호 일 때 stack 에 pop
 * - stack 이 비어있으면 true
 */
public class Q20_ValidParentheses {
    private final Map<Character, Character> table = Map.of(
            '}', '{',
            ')', '(',
            ']', '['
    );

    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();

        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if (!table.containsKey(c)) {
                stack.push(c);
            } else if (stack.isEmpty() || table.get(c) != stack.pop()) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("()", true),
                Arguments.of("()[]{}", true),
                Arguments.of("(]", false),
                Arguments.of("((()))", true),
                Arguments.of("(({)))", false)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String s, boolean expected) {
        boolean result = new Q20_ValidParentheses().isValid(s);
        assertThat(result).isEqualTo(expected);
    }
}
