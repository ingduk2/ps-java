package ps.become_a_successful_coding_test_candidate.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 26 예상 대진표
- N 명의 참가자는 1 <-> 2, 3 <-> 4, n-1 <-> n 게임 진행
- 다음 라운드에 진출할 참가자는 다시 1 ~ N/2 배정
- A 번 참가자는 B 번 참가자와 몇 번쨰 라운드에서 만나는지 궁금
- 만나기 전까지 항상 이긴다고 가정

풀이
- n = 8, a = 4, b = 7
-        | |
-      4      7
-   1/2 4  5/6  7
- 1 2  3 4 5 6 7 8
- 참가자는 (참가자 번호 + 1) / 2 로 다음 라운드의 번호를 부여 받음
- a 와 b 의 번호가 같다면, 두 참가자 중 하나가 올라온 경우,
- 결과에서 -1 을 하면 만난 라운드
 */
public class Problem26 {

    static class Solution1 {
        public int solution(int n, int a, int b) {
            int round = 1;

            while (a != b) {
                a = (a + 1) / 2;
                b = (b + 1) / 2;
                round++;
            }

            return round - 1;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(8, 4, 7, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, int a, int b, int expected) {
        assertThat(new Solution1().solution(n, a, b)).isEqualTo(expected);
    }
}
