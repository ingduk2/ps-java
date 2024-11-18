package ps.become_a_successful_coding_test_candidate.hash;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 20 할인 행사
할인하는 제품은 하루에 하나씩만 구매
자신이 원하는 제품과 수량이 할인하는 날짜와 10일 연속으로 일치할 경우에 회원 가입

풀이
Map 생성
- 내가 사려고 하는 Map<품목, 개수> 를 만든다
- 각 시작 일자별로 할인 받을 수 있는 Map<품목, 개수> 를 만든다
- 가능한 일수를 더한다

슬라이딩 윈도우 기법
- 내가 사려고 하는 Map<품목, 개수> 를 만든다
- 일자별로 Map 에서 이전 값 제거, 새로 추가 된 값 업데이트
 */
public class Problem20 {

    static class Solution1 {
        public int solution(String[] want, int[] number, String[] discount) {
            Map<String, Integer> wantMap = new HashMap<>();
            for (int i = 0; i < want.length; i++) {
                wantMap.put(want[i], number[i]);
            }

            int result = 0;
            for (int i = 0; i < discount.length - 10 + 1; i++) {
                Map<String, Integer> discountMap = new HashMap<>();
                for (int j = i; j < i + 10; j++) {
                    discountMap.put(discount[j], discountMap.getOrDefault(discount[j], 0) + 1);
                }

                if (discountMap.equals(wantMap)) {
                    result++;
                }
            }

            return result;
        }
    }

    static class Solution2 {
        public int solution(String[] want, int[] number, String[] discount) {
            Map<String, Integer> wantMap = new HashMap<>();
            for (int i = 0; i < want.length; i++) {
                wantMap.put(want[i], number[i]);
            }

            int result = 0;
            Map<String, Integer> discountMap = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
            }

            if (wantMap.equals(discountMap)) {
                result++;
            }

            for (int i = 10; i < discount.length; i++) {
                String removeItem = discount[i - 10];
                discountMap.put(removeItem, discountMap.get(removeItem) - 1);
                if (discountMap.get(removeItem) == 0) {
                    discountMap.remove(removeItem);
                }

                String newItem = discount[i];
                discountMap.put(newItem, discountMap.getOrDefault(newItem, 0) + 1);

                if (wantMap.equals(discountMap)) {
                    result++;
                }
            }

            return result;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"banana", "apple", "rice", "pork", "pot"},
                        new int[]{3, 2, 2, 2, 1},
                        new String[]{"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"},
                        3
                ),
                Arguments.of(
                        new String[]{"apple"},
                        new int[]{10},
                        new String[]{"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"},
                        0
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] wang, int[] number, String[] discount, int expected) {
        assertThat(new Solution1().solution(wang, number, discount)).isEqualTo(expected);
        assertThat(new Solution2().solution(wang, number, discount)).isEqualTo(expected);
    }
}
