package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 * - 중복 문자열 없이 가장 긴 subString 길이 찾기
 *
 * 1. 슬라이딩 윈도우와 투 포인터로 크기 조절
 * - left, right, used(Map) 최종 위치 저장
 * - 중복 문자가 나오고 최종 위치 이전 이라면, left 를 나왔던 최종 위치 + 1
 * - 처음 보는 문자라면 최대 길이 업데이트 (right - left + 1)
 * -
 * - a b c a b c b b c
 *   1
 *     2
 *       3
 *         3
 *           3
 *             3
 *               2
 *                 1
 *                   2
 */
public class Q32_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        // 문자 최종 위치 저장
        Map<Character, Integer> used = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        int right = 0;

        for (char c : s.toCharArray()) {
            // 이미 등장했던 문자이고, 슬라이딩 윈도우의 안쪽에 있다면 left 위치 업데이트
            if (used.containsKey(c) && left <= used.get(c)) {
                left = used.get(c) + 1;
            } else { // 처음 보는 문자라면 최대 길이 문자열 길이 업데이트
                maxLength = Math.max(maxLength, right - left + 1);
            }

            // 현재 문자의 위치 삽입
            used.put(c, right);
            right++;
        }

        return maxLength;
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("abcabcbb", 3),
                Arguments.of("abbabcbb", 3),
                Arguments.of("bbbbb", 1),
                Arguments.of("pwwkew", 3)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String s, int expected) {
        int result = new Q32_LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring(s);
        assertThat(result).isEqualTo(expected);
    }
}
