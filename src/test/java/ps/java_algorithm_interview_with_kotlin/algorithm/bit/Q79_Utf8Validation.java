package ps.java_algorithm_interview_with_kotlin.algorithm.bit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/utf-8-validation/description/
 * - UTF-8 검증
 *
 * 1. 첫 바이트를 기준으로 한 판별
 * - 2byte 문자, 1byte 문자 일 수도 있음
 */
public class Q79_Utf8Validation {

    static class Solution1 {
        public boolean validUtf8(int[] data) {
            int start = 0;

            while (start < data.length) {
                int first = data[start];
                // 11110 으로 시작하면 나머지 3 바이트
                if (first >> 3 == 0b11110 && check(data, start, 3)) {
                    start += 4;
                }
                // 1110 으로 시작하면 나머지 2 바이트
                else if (first >> 4 == 0b1110 && check(data, start, 2)) {
                    start += 3;
                }
                // 110 으로 시작하면 나머지 1 바이
                else if (first >> 5 == 0b110 && check(data, start, 1)) {
                    start += 2;
                }
                // 1 바이트 문자
                else if (first >> 7 == 0) {
                    start++;
                } else {
                    return false;
                }
            }

            return true;
        }

        private boolean check(int[] data, int start, int size) {
            for (int i = start + 1; i < start + size + 1; i++) {
                if (i >= data.length || data[i] >> 6 != 0b10) {
                    return false;
                }
            }

            return true;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{197,130,1},
                        true
                ),Arguments.of(
                        new int[]{235,140,4},
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] data, boolean expected) {
        boolean result = new Solution1().validUtf8(data);
        assertThat(result).isEqualTo(expected);
    }
}
