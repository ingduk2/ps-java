package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/remove-duplicate-letters/description/
 * - 사전식 순서
 *
 * 1. 재귀를 이용한 분리
 * - 중복 문자를 제외한 알파벳 순 문자열 정렬
 * -
 *
 * 2. 스택을 이용한 문자 제거
 * - 스택에 있는 문자가 현재 문자보다 더 뒤에 붙여야 할 문자이고, 아직 처리해야 할 문자가 남아 있다면 스택에서 제, 처리하지 않은 걸로 표시
 * - 이미 처리한 문자는 건너 띈다
 */
public class Q21_RemoveDuplicateLetters {

    static class RecursiveSolve {
        public String removeDuplicateLetters(String s) {
            Set<Character> sortedSet = toSortedSet(s);
            for (char c : sortedSet) {
                String suffix = s.substring(s.indexOf(c));
                if (toSortedSet(s).equals(toSortedSet(suffix))) {
                    return c + removeDuplicateLetters(suffix.replace(String.valueOf(c), ""));
                }
            }

            return "";
        }

        private Set<Character> toSortedSet(String s) {
            Set<Character> set = new TreeSet<>(Character::compareTo);
            for (int i = 0; i < s.length(); i++) {
                set.add(s.charAt(i));
            }

            return set;
        }
    }

    static class StackSolve {
        public String removeDuplicateLetters(String s) {
            Map<Character, Integer> counter = new HashMap<>();
            Map<Character, Boolean> seen = new HashMap<>();
            Deque<Character> stack = new ArrayDeque<>();

            // counting
            for (char c : s.toCharArray()) {
                int count = counter.getOrDefault(c, 0);
                counter.put(c, count + 1);
            }

            for (char c : s.toCharArray()) {
                // count -1
                counter.put(c, counter.get(c) - 1);
                // 이미 처리 된 문자는 건너 띈다
                if (seen.get(c) != null && seen.get(c)) {
                    continue;
                }
                // 스택에 있는 문자가 현재 문자보다 더 뒤에 붙여야 할 문자라면 스택에서 제거하고
                // 처리하지 않은 걸로 표시
                while (!stack.isEmpty() && stack.peek() > c && counter.get(stack.peek()) > 0) {
                    seen.put(stack.poll(), false);
                }
                // stack push
                stack.push(c);
                // 현재 문자를 처리 한 걸로 선언
                seen.put(c, true);
            }

            // result
            StringBuilder result = new StringBuilder();
            while (!stack.isEmpty()) {
                result.append(stack.pollLast());
            }
            return result.toString();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("bcabc", "abc"),
                Arguments.of("cbacdcbc", "acdb"),
                Arguments.of("dbacdcbc", "acdb"),
                Arguments.of("ebcabc", "eabc"),
                Arguments.of("ebcabce", "abce")
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String s, String expected) {
        // Recursive
        String result = new RecursiveSolve().removeDuplicateLetters(s);
        assertThat(result).isEqualTo(expected);

        // Stack
        String result2 = new StackSolve().removeDuplicateLetters(s);
        assertThat(result2).isEqualTo(expected);
    }
}
