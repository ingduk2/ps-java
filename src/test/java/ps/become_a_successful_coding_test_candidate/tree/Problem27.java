package ps.become_a_successful_coding_test_candidate.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 27 다단계 칫솔 판매
- 판매액 100원
- 상위로 10% 씩 수익금을 나누어 주고, 1원 미만인 경우 자신이 모두 가짐

풀이
- Map<enroll, referral> 을 생성
- Map<name, 수익> 을 생성
- seller for
-- 첫 seller 로 반복해서 Map<enroll, referral>.get 하면 상위 referral 가 계속 나온다
-- money > 0 && "-" 가 아닌 경우
-- 금액 - (금액 / 10) 을 Map<name, 수익> 에 저장
- enroll 순서대로 수익 map 에서 결과값을 만든다
 */
public class Problem27 {

    static class Solution1 {
        public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
            Map<String, String> enrollReferralMap = new HashMap<>();
            for (int i = 0; i < enroll.length; i++) {
                enrollReferralMap.put(enroll[i], referral[i]);
            }

            Map<String, Integer> profit = new HashMap<>();
            for (int i = 0; i < seller.length; i++) {
                String curName = seller[i];
                int money = amount[i] * 100;

                while (money > 0 && !curName.equals("-")) {
                    profit.put(curName, profit.getOrDefault(curName, 0) + money - (money / 10));
                    curName = enrollReferralMap.get(curName);
                    money /= 10;
                }
            }

            int[] answer = new int[enroll.length];
            for (int i = 0; i < enroll.length; i++) {
                answer[i] = profit.getOrDefault(enroll[i], 0);
            }

            return answer;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                        new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                        new String[]{"young", "john", "tod", "emily", "mary"},
                        new int[]{12, 4, 2, 5, 10},
                        new int[]{360, 958, 108, 0, 450, 18, 180, 1080}
                ),
                Arguments.of(
                        new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                        new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                        new String[]{"sam", "emily", "jaimie", "edward"},
                        new int[]{2, 3, 5, 4},
                        new int[]{0, 110, 378, 180, 270, 450, 0, 0}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] enroll, String[] referral, String[] seller, int[] amount, int[] expected) {
        assertThat(new Solution1().solution(enroll, referral, seller, amount)).isEqualTo(expected);
    }
}
