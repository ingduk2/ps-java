package ps.programmers_coding_test.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 9 3진법 뒤집기 - Level 1

풀이
- 1. n 을 3진법으로 만든다
- 2. 앞뒤로 뒤집는다
- 3. 다시 10진법으로 표현
 */
public class Problem9 {

    static class Solution1 {
        public int solution(int n) {
            String base3 = Integer.toString(n, 3);
            String reverse = reverseString(base3);
            int base10 = Integer.parseInt(reverse, 3);

            return base10;
        }

        private String reverseString(String three) {
            return new StringBuilder(three).reverse().toString();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(45, 7),
                Arguments.of(125, 229)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, int expected) {
        assertThat(new Solution1().solution(n)).isEqualTo(expected);
    }
}
