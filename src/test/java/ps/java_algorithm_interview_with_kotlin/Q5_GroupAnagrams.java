package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/group-anagrams/description/
 * - 순서는 상관 없다
 * - anagram 별로 모아서 리스트로 만들자
 *
 * - 조합? 복잡하다
 * - 단어를 잘라서 char 의 합이 같은 값이면 같은 값으로 볼 수 있지 않을까
 * --> 실패, 단어가 달라도 합이 같을 수 있다
 * - 단어 합 + 알파벳순정렬로 키를 바꿔보자
 * --> 그냥 알파벳순정렬을 키로 하면 될 듯
 */
public class Q5_GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> grouping = Arrays.stream(strs)
                .collect(Collectors.groupingBy(str -> {
                    char[] charArray = str.toCharArray();
                    Arrays.sort(charArray);
                    return new String(charArray);
                }));

        return grouping.values()
                .stream()
                .toList();
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"eat","tea","tan","ate","nat","bat"},
                        List.of(
                                List.of("bat"),
                                List.of("nat", "tan"),
                                List.of("ate", "eat", "tea")
                        )
                ),
                Arguments.of(
                        new String[]{""},
                        List.of(
                                List.of("")
                        )
                ),
                Arguments.of(
                        new String[]{"a"},
                        List.of(
                                List.of("a")
                        )
                ),
                Arguments.of(
                        new String[]{"cab","tin","pew","duh","may","ill","buy","bar","max","doc"},
                        List.of(
                                List.of("max"),
                                List.of("buy"),
                                List.of("doc"),
                                List.of("may"),
                                List.of("ill"),
                                List.of("duh"),
                                List.of("tin"),
                                List.of("bar"),
                                List.of("pew"),
                                List.of("cab")
                        )
                )
        );
    }
    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] strs, List<List<String>> expected) {
        // When
        List<List<String>> result = new Q5_GroupAnagrams().groupAnagrams(strs);

        // Then
        List<List<String>> sortedExpected = expected.stream()
                .map(list -> list.stream().sorted().collect(Collectors.toList()))
                .sorted(Comparator.comparing(Object::toString))
                .toList();

        List<List<String>> sortedResult = result.stream()
                .map(list -> list.stream().sorted().collect(Collectors.toList()))
                .sorted(Comparator.comparing(Object::toString))
                .toList();
        assertThat(sortedResult).containsExactlyElementsOf(sortedExpected);
    }
}
