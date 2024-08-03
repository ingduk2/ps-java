package ps.java_algorithm_interview_with_kotlin.algorithm.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/valid-anagram/description/
 * 유효한 아나그램
 *
 * 1. 정렬을 이용한 비교
 */
public class Q68_ValidAnagram {

    static class Solution1 {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            String sStr = sort(s);
            String tStr = sort(t);

            return sStr.equals(tStr);
        }

        public String sort(String str) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            return new String(charArray);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        "anagram", "nagaram",
                        true
                ),
                Arguments.of(
                        "rat", "car",
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String s, String t, boolean expected) {
        boolean result = new Solution1().isAnagram(s, t);
        assertThat(result).isEqualTo(expected);
    }
}
