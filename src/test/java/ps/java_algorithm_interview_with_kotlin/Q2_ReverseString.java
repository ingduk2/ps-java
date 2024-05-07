package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/reverse-string/
 * - in-place
 */
public class Q2_ReverseString {

    public void reverseString(char[] s) {
        int start = 0;
        int end = s.length - 1;
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;

            start++;
            end--;
        }
    }

    public void solution1(char[] s) {
        int length = s.length;
        for (int i = 0; i < Math.floor((double) (length) / 2); i++) {
            char tmp = s[i];
            s[i] = s[length - 1 - i];
            s[length - 1 - i] = tmp;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new char[]{'h', 'e', 'l', 'l', 'o'}, new char[]{'o', 'l', 'l', 'e', 'h'}),
                Arguments.of(new char[]{'H', 'a', 'n', 'n', 'a', 'h'}, new char[]{'h', 'a', 'n', 'n', 'a', 'H'}),
                Arguments.of(new char[]{'a'}, new char[]{'a'}),
                Arguments.of(new char[]{'a', 'b'}, new char[]{'b', 'a'}),
                Arguments.of(new char[]{'a', 'b', 'c'}, new char[]{'c', 'b', 'a'}),
                Arguments.of(new char[]{'a', 'b', 'c', 'd'}, new char[]{'d', 'c', 'b', 'a'}),
                Arguments.of(new char[]{'a', 'b', 'c', 'd', 'e'}, new char[]{'e', 'd', 'c', 'b', 'a'})
        );
    }
    @ParameterizedTest
    @MethodSource("arguments")
    void test(char[] input, char[] expected) {
        Q2_ReverseString problem = new Q2_ReverseString();

        problem.reverseString(input);
        assertThat(input).containsExactly(expected);
    }
}
