package ps.java_algorithm_interview_with_kotlin.algorithm.greedy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/assign-cookies/
 * - g 만족하는 쿠키의 최소 사이즈, s 쿠키 사이즈
 * - 최대 쿠키를 받을 수 있는 아이 수
 *
 * - Solution1
 * -- 정렬하여, 배분
 * -- [1,2,3,4] [1,1] -> 1
 * -- [1,2,3,4] [2,3,4,5,6] -> 4
 * -- child, cookie 두 개의 포인터로
 * -- if (g[child] <= s[cookie]) child++ 다음 아이로
 * -- cookie 는 계속 이동시킨다
 *
 */
public class Q88_AssignCookies {

    static class Solution1 {
        public int findContentChildren(int[] g, int[] s) {
            Arrays.sort(g);
            Arrays.sort(s);

            int childIdx = 0;
            int cookieIdx = 0;

            while (childIdx < g.length && cookieIdx < s.length) {
                if (g[childIdx] <= s[cookieIdx]) {
                    childIdx++;
                }
                cookieIdx++;
            }

            return childIdx;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, new int[]{1, 1}, 1),
                Arguments.of(new int[]{1, 2}, new int[]{1, 2, 3}, 2),
                Arguments.of(new int[]{1, 2, 3, 4}, new int[]{1, 1}, 1),
                Arguments.of(new int[]{1, 2, 3, 4}, new int[]{2, 3, 4, 5, 6}, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] g, int[] s, int expected) {
        int result = new Solution1().findContentChildren(g, s);
        assertThat(result).isEqualTo(expected);
    }
}
