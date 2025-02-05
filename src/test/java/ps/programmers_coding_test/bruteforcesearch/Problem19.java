package ps.programmers_coding_test.bruteforcesearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 19 - 카펫 - Level2

풀이
- 가로 길이가 세로 길이보다 크거나 같다
  - 3 <= width <= 5000
  - 3 <= height <= width
- 갈색 개수 width * 2 + (height - 2) * 2 = (width + height - 2) * 2
- 노란 개수 width * height - (갈색 개수)
 */
public class Problem19 {

    static class Solution1 {
        public int[] solution(int brown, int yellow) {
            for (int width = 3; width <= 5000; width++) {
                for (int height = 3; height <= width; height++) {
                    int boundary = (width + height - 2) * 2;
                    int center = width * height - boundary;
                    if (brown == boundary && yellow == center) {
                        return new int[] {width, height};
                    }
                }
            }

            return null;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(10, 2, new int[]{4, 3}),
                Arguments.of(8, 1, new int[]{3, 3}),
                Arguments.of(24, 24, new int[]{8, 6})
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int brown, int yellow, int[] expected) {
        assertThat(new Solution1().solution(brown, yellow)).isEqualTo(expected);
    }
}
