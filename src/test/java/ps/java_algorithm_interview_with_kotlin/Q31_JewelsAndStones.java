package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/jewels-and-stones/description/
 * - jewels : 보석의 유형을 나타냄
 * - stones : 가지고 있는 돌
 * - 돌 중에 보석인 돌이 몇 개인지
 *
 * 1. stones 각 문자열을 hashMap 에 counting 해서 저장
 * - jewels 를 각각 map 에서 찾아서 count 를 더한다
 *
 * 2. jewels 각 문자열을 Set 에 저장
 * - stones 를 돌면서 포함되는 경우 count
 */
public class Q31_JewelsAndStones {

    static class HashMapSolve {
        public int numJewelsInStones(String jewels, String stones) {
            // stone counting
            Map<Character, Integer> stoneCounts = new HashMap<>();
            for (char c : stones.toCharArray()) {
                if (stoneCounts.containsKey(c)) {
                    stoneCounts.put(c, stoneCounts.get(c) + 1);
                } else {
                    stoneCounts.put(c, 1);
                }
            }

            int result = 0;
            for (char c : jewels.toCharArray()) {
                Integer count = stoneCounts.get(c);
                if (count != null) {
                    result += count;
                }
            }

            return result;
        }
    }

    static class SetSolve {
        public int numJewelsInStones(String jewels, String stones) {
            Set<Character> jewelSet = new HashSet<>();
            for (char j : jewels.toCharArray()) {
                jewelSet.add(j);
            }

            int result = 0;
            for (char s : stones.toCharArray()) {
                if (jewelSet.contains(s)) result++;
            }

            return result;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        "aA",
                        "aAAbbbb",
                        3
                ),
                Arguments.of(
                        "z",
                        "ZZ",
                        0
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String jewels, String stones, int expected) {
        int result = new HashMapSolve().numJewelsInStones(jewels, stones);
        assertThat(result).isEqualTo(expected);

        int result2 = new SetSolve().numJewelsInStones(jewels, stones);
        assertThat(result2).isEqualTo(expected);
    }
}
