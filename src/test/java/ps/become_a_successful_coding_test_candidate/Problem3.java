package ps.become_a_successful_coding_test_candidate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 03 두 개 뽑아서 더하기
https://school.programmers.co.kr/learn/courses/30/lessons/68644?language=java

서로 다른 인덱스에 있는 두 수를 뽑아 더해서, 모든 수를 오름차순

제한사항
- numbers 의 길이는 2 이상 100 이하입니다. -> O(N^2)
- numbers 의 모든 수는 0 이상 100 이하입니다.

풀이
- 서로 다른 인덱스 조합
- 중복 제거 저장
- 오름차순 정렬
 */
public class Problem3 {

    static class Solution1 {
        public int[] solution(int[] numbers) {
            Set<Integer> set = new TreeSet<>();
            for (int i = 0; i < numbers.length; i++) {
                for (int j = i + 1; j < numbers.length; j++) {
                    set.add(numbers[i] + numbers[j]);
                }
            }

            return set.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int[]{2, 1, 3, 4, 1}, new int[]{2, 3, 4, 5, 6, 7}),
                Arguments.of(new int[]{5, 0, 2, 7}, new int[]{2, 5, 7, 9, 12})
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] numbers, int[] expected) {
        int[] result = new Solution1().solution(numbers);
        assertThat(result).isEqualTo(expected);
    }
}
