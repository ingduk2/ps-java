package ps.programmers_coding_test.string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 5 자연수 뒤집어 배열로 만들기 - Level 1
 */
public class Problem5 {

    static class Solution1 {
        public int[] solution(long n) {
            String str = String.valueOf(n);
            String reverseStr = new StringBuilder(str).reverse().toString();

            int[] answer = new int[reverseStr.length()];
            for (int i = 0; i < reverseStr.length(); i++) {
                answer[i] = Character.getNumericValue(reverseStr.charAt(i));
            }
            return answer;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        12345,
                        new int[]{5, 4, 3, 2, 1}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(long n, int[] expected) {
        assertThat(new Solution1().solution(n)).isEqualTo(expected);
    }
}
