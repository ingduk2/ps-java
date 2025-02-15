package ps.programmers_coding_test.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 28 - 가장 큰 수 - Level 2

풀이
- [정수를 이어붙여 가장 큰 수 만들기]
- 3, 30 | 99, 90 인 경우에 순서대로 이어보고, 바꾸어서 이어 본 후 더 큰 수를 선택
 */
public class Problem28 {

    static class Solution1 {
        public String solution(int[] numbers) {
            return Arrays.stream(numbers)
                    .mapToObj(String::valueOf)
                    .sorted((o1, o2) -> {
                        int number1 = Integer.parseInt(o1 + o2);
                        int number2 = Integer.parseInt(o2 + o1);
                        return number2 - number1;
                    })
                    .collect(Collectors.joining(""))
                    .replaceAll("^0+", "0");
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int[]{6, 10, 2}, "6210"),
                Arguments.of(new int[]{3, 30, 34, 5, 9}, "9534330")
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] numbers, String expected) {
        assertThat(new Solution1().solution(numbers)).isEqualTo(expected);
    }
}
