package ps.become_a_successful_coding_test_candidate.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 09 10진수를 2진수로 변환하기

- 풀이
-- 직접 계산

-- 스택

-- API
 */
public class Problem9 {

    static class Solution1 {
        public String solution(int decimal) {
            StringBuilder result = new StringBuilder();

            while (decimal > 0) {
                int remainder = decimal % 2;
                result.append(remainder);
                decimal /= 2;
            }

            return result.reverse().toString();
        }
    }

    static class Solution2 {
        public String solution(int decimal) {
            Deque<Integer> stack = new ArrayDeque<>();

            while (decimal > 0) {
                int remainder = decimal % 2;
                stack.push(remainder);
                decimal /= 2;
            }

            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }

            return sb.toString();
        }
    }

    static class Solution3 {
        public String solution(int decimal) {
            return Integer.toBinaryString(decimal);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(10, "1010"),
                Arguments.of(13, "1101"),
                Arguments.of(27, "11011"),
                Arguments.of(12345, "11000000111001")
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int decimal, String expected) {
        String result = new Solution1().solution(decimal);
        assertThat(result).isEqualTo(expected);

        String result2 = new Solution2().solution(decimal);
        assertThat(result2).isEqualTo(expected);

        String result3 = new Solution3().solution(decimal);
        assertThat(result3).isEqualTo(expected);
    }
}
