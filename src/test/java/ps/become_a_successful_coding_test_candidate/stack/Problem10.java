package ps.become_a_successful_coding_test_candidate.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 10 괄호 회전하기
- 회전 시켰을 때, s 가 올바른 괄호 문자열이 되게 하는 x 의 개수

제한사항
- s의 길이는 1 이상 1,000 이하입니다.

풀이
- 회전을 시킨다 (s를 왼쪽으로 x칸만큼 회전)
- 올바른 괄호 문자열면 결과 + 1
 */
public class Problem10 {

    static class Solution1 {
        public int solution(String s) {
            int result = 0;

            int locationCount = s.length();
            for (int i = 0; i < locationCount; i++) {
                if (isCorrect(s, i, s.length())) {
                    result++;
                }
            }

            return result;
        }

        private boolean isCorrect(String str, int start, int length) {
            Deque<Character> stack = new ArrayDeque<>();

            for (int i = start; i < length + start; i++) {
                char c = str.charAt(i % length);
                if (c == '[' || c == '(' || c == '{') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) {
                        return false;
                    }

                    char open = stack.pop();
                    if ((open == '[' && c != ']') ||
                            (open == '(' && c != ')') ||
                            (open == '{' && c != '}')) {
                        return false;
                    }
                }
            }

            return stack.isEmpty();
        }
    }

    static class Solution2 {
        private final Map<Character, Character> map = Map.of(
                ')', '(',
                '}', '{',
                ']', '['
        );

        public int solution(String s) {
            int result = 0;

            int locationCount = s.length();
            for (int i = 0; i < locationCount; i++) {
                if (isCorrect(s, i, s.length())) {
                    result++;
                }
            }

            return result;
        }

        private boolean isCorrect(String str, int start, int length) {
            Deque<Character> stack = new ArrayDeque<>();

            for (int i = start; i < length + start; i++) {
                char c = str.charAt(i % length);
                if (!map.containsKey(c)) {
                    stack.push(c);
                } else {
                    if (stack.isEmpty() || stack.pop() != map.get(c)) {
                        return false;
                    }
                }
            }

            return stack.isEmpty();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("[](){}", 3),
                Arguments.of("}]()[{", 2),
                Arguments.of("[)(]", 0),
                Arguments.of("}}}", 0)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String s, int expected) {
        int result = new Solution1().solution(s);
        assertThat(result).isEqualTo(expected);

        int result2 = new Solution2().solution(s);
        assertThat(result2).isEqualTo(expected);
    }
}
