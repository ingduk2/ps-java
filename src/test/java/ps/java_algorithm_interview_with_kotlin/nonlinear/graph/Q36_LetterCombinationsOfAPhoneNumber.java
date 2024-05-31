package ps.java_algorithm_interview_with_kotlin.nonlinear.graph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 * - 2~9 숫자에 해당하는 가능한 문자 조합
 * 1. DFS Recursive (23)
 * - result: [] digits: 23 index: 0 path:
 * - 2 [a, b, c]
 * - result: [] digits: 23 index: 1 path: a
 * - 3 [d, e, f]
 * - result: [] digits: 23 index: 2 path: ad
 * - result: [ad] digits: 23 index: 2 path: ae
 * - result: [ad, ae] digits: 23 index: 2 path: af
 * - result: [ad, ae, af] digits: 23 index: 1 path: b
 * - 3 [d, e, f]
 * - result: [ad, ae, af] digits: 23 index: 2 path: bd
 * - result: [ad, ae, af, bd] digits: 23 index: 2 path: be
 * - result: [ad, ae, af, bd, be] digits: 23 index: 2 path: bf
 * - result: [ad, ae, af, bd, be, bf] digits: 23 index: 1 path: c
 * - 3 [d, e, f]
 * - result: [ad, ae, af, bd, be, bf] digits: 23 index: 2 path: cd
 * - result: [ad, ae, af, bd, be, bf, cd] digits: 23 index: 2 path: ce
 * - result: [ad, ae, af, bd, be, bf, cd, ce] digits: 23 index: 2 path: cf
 *
 * 2. DFS Iterative (23)
 * - 1. stack: []
 * - 2. current:
 * - 3. letters: [a, b, c]
 * - 1. stack: [c, b, a]
 * - 2. current: c
 * - 3. letters: [d, e, f]
 * - 1. stack: [cf, ce, cd, b, a]
 * - 2. current: cf
 * - 1. stack: [ce, cd, b, a]
 * - 2. current: ce
 * - 1. stack: [cd, b, a]
 * - 2. current: cd
 * - 1. stack: [b, a]
 * - 2. current: b
 * - 3. letters: [d, e, f]
 * - 1. stack: [bf, be, bd, a]
 * - 2. current: bf
 * - 1. stack: [be, bd, a]
 * - 2. current: be
 * - 1. stack: [bd, a]
 * - 2. current: bd
 * - 1. stack: [a]
 * - 2. current: a
 * - 3. letters: [d, e, f]
 * - 1. stack: [af, ae, ad]
 * - 2. current: af
 * - 1. stack: [ae, ad]
 * - 2. current: ae
 * - 1. stack: [ad]
 * - 2. current: ad
 *
 * 3. BFS (23)
 * - 1. queue: []
 * - 2. current:
 * - 3. letters: [a, b, c]
 * - 1. queue: [a, b, c]
 * - 2. current: a
 * - 3. letters: [d, e, f]
 * - 1. queue: [b, c, ad, ae, af]
 * - 2. current: b
 * - 3. letters: [d, e, f]
 * - 1. queue: [c, ad, ae, af, bd, be, bf]
 * - 2. current: c
 * - 3. letters: [d, e, f]
 * - 1. queue: [ad, ae, af, bd, be, bf, cd, ce, cf]
 * - 2. current: ad (return)
 * - 1. queue: [ae, af, bd, be, bf, cd, ce, cf]
 * - 2. current: ae (return)
 * - 1. queue: [af, bd, be, bf, cd, ce, cf]
 * - 2. current: af (return)
 * - 1. queue: [bd, be, bf, cd, ce, cf]
 * - 2. current: bd (return)
 * - 1. queue: [be, bf, cd, ce, cf]
 * - 2. current: be (return)
 * - 1. queue: [bf, cd, ce, cf]
 * - 2. current: bf (return)
 * - 1. queue: [cd, ce, cf]
 * - 2. current: cd (return)
 * - 1. queue: [ce, cf]
 * - 2. current: ce (return)
 * - 1. queue: [cf]
 * - 2. current: cf (return)
 */
public class Q36_LetterCombinationsOfAPhoneNumber {
    private static final Map<Character, List<Character>> phoneDigits = new HashMap<>();

    static {
        phoneDigits.put('0', List.of());
        phoneDigits.put('1', List.of());
        phoneDigits.put('2', List.of('a', 'b', 'c'));
        phoneDigits.put('3', List.of('d', 'e', 'f'));
        phoneDigits.put('4', List.of('g', 'h', 'i'));
        phoneDigits.put('5', List.of('j', 'k', 'l'));
        phoneDigits.put('6', List.of('m', 'n', 'o'));
        phoneDigits.put('7', List.of('p', 'q', 'r', 's'));
        phoneDigits.put('8', List.of('t', 'u', 'v'));
        phoneDigits.put('9', List.of('w', 'x', 'y', 'z'));
    }

    static class DFSRecursiveSolve {
        public List<String> letterCombinations(String digits) {
            List<String> result = new ArrayList<>();

            if (digits.isEmpty()) {
                return result;
            }

            dfs(result, digits, 0, new StringBuilder());

            return result;
        }

        private void dfs(List<String> result, String digits, int index, StringBuilder path) {
            if (path.length() == digits.length()) {
                result.add(String.valueOf(path));
                return;
            }

            for (char c : phoneDigits.get(digits.charAt(index))) {
                dfs(result, digits, index + 1, new StringBuilder(path).append(c));
            }
        }
    }

    static class DFSIterativeSolve {
        public List<String> letterCombinations(String digits) {
            List<String> result = new ArrayList<>();

            if (digits.isEmpty()) {
                return result;
            }

            Deque<StringBuilder> stack = new ArrayDeque<>();
            stack.push(new StringBuilder());

            while (!stack.isEmpty()) {
                StringBuilder current = stack.pop();
                if (current.length() == digits.length()) {
                    result.add(current.toString());
                } else {
                    List<Character> letters = phoneDigits.get(digits.charAt(current.length()));
                    for (char letter : letters) {
                        StringBuilder next = new StringBuilder(current);
                        next.append(letter);
                        stack.push(next);
                    }
                }
            }

            return result;
        }
    }

    static class BFSSolve {
        public List<String> letterCombinations(String digits) {
            List<String> result = new ArrayList<>();

            if (digits.isEmpty()) {
                return result;
            }

            Queue<StringBuilder> queue = new LinkedList<>();
            queue.add(new StringBuilder());

            while (!queue.isEmpty()) {
                StringBuilder current = queue.poll();
                if (current.length() == digits.length()) {
                    result.add(current.toString());
                } else {
                    List<Character> letters = phoneDigits.get(digits.charAt(current.length()));
                    for (char letter : letters) {
                        StringBuilder next = new StringBuilder(current);
                        next.append(letter);
                        queue.add(next);
                    }
                }
            }

            return result;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        "23",
                        Arrays.asList("ad","ae","af","bd","be","bf","cd","ce","cf")
                ),
                Arguments.of(
                        "",
                        Arrays.asList()
                ),
                Arguments.of(
                        "2",
                        Arrays.asList("a", "b", "c")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String digits, List<String> expected) {
        List<String> result = new DFSRecursiveSolve().letterCombinations(digits);
        assertThat(result).isEqualTo(expected);

        List<String> result2 = new DFSIterativeSolve().letterCombinations(digits);
        assertThat(result2).containsExactlyInAnyOrderElementsOf(expected);

        List<String> result3 = new BFSSolve().letterCombinations(digits);
        assertThat(result3).isEqualTo(expected);
    }
}
