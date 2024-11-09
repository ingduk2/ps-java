package ps.become_a_successful_coding_test_candidate.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 11 짝지어 제거하기
알파벳이 2개 붙어 있는 짝을 찾아서 제거, 반복해서 전부 제거되면, 1, 안되면 0

제한사항
- 문자열의 길이 : 1,000,000이하의 자연수
- 문자열은 모두 소문자로 이루어져 있습니다.

풀이
- stack 이 비어있지 않고, peek == c 이면 pop
- stack 이 비어있지 않고, peek != c 이면 push
- stack 이 비어있으면 push
 */
public class Problem11 {

    static class Solution1 {
        public int solution(String s) {
            Deque<Character> stack = new ArrayDeque<>();
            for (char c : s.toCharArray()) {
                if (!stack.isEmpty() && stack.peek() == c) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }

            return stack.isEmpty() ? 1 : 0;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("baabaa", 1),
                Arguments.of("cdcd", 0)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String s, int expected) {
        int result = new Solution1().solution(s);
        assertThat(result).isEqualTo(expected);
    }
}
