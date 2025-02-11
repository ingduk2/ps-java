package ps.programmers_coding_test.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 25 H-Index - Level 2

풀이
- 논문 n 편 중 h 번 이상 인용된 논문이 h 편 이상이고, 나머지 논문이 h 번 이하 인용되었다면 h의 최댓값
- 정렬하고
- H번 이상 인용된 논문이 H편 이상이면 -> 나머지는 H번 이하 인용된 논문 만족 (n - h)
 */
public class Problem25 {

    static class Solution1 {
        public int solution(int[] citations) {
            int n = citations.length;
            Arrays.sort(citations);

            for (int i = 0; i < n; i++) {
                int h = n - i;
                if (citations[i] >= h) {
                    return h;
                }
            }

            return 0;
        }
    }

    static class Solution2 {
        public int solution(int[] citations) {
            Arrays.sort(citations);
            for (int h = citations.length; h >= 1; h--) {
                if (isValid(citations, h)) return h;
            }

            return 0;
        }

        private boolean isValid(int[] citations, int h) {
            int index = citations.length - h;
            return citations[index] >= h;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int[]{3, 0, 6, 1, 5}, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] citations, int expected) {
        assertThat(new Solution1().solution(citations)).isEqualTo(expected);
        assertThat(new Solution2().solution(citations)).isEqualTo(expected);
    }
}
