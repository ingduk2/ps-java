package ps.programmers_coding_test.bruteforcesearch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 22 - 불량 사용자 - Level 3

풀이
- 각 불량 사용자 아이디 목록을 구한다
- 조합 할 수 있는 경우의 수를 구한다
 */
public class Problem22 {

    static class Solution1 {
        public int solution(String[] user_id, String[] banned_id) {
            String[][] bans = Arrays.stream(banned_id)
                    .map(banned -> banned.replace('*', '.'))
                    .map(banned -> Arrays.stream(user_id)
                            .filter(id -> id.matches(banned))
                            .toArray(String[]::new))
                    .toArray(String[][]::new);

            Set<Set<String>> combinations = new HashSet<>();
            dfs(bans, new HashSet<>(), 0, combinations);

            return combinations.size();
        }

        private void dfs(String[][] bans, Set<String> selected, int depth, Set<Set<String>> combinations) {
            if (depth == bans.length) {
                combinations.add(new HashSet<>(selected));
                return;
            }

            for (String user : bans[depth]) {
                if (selected.contains(user)) continue;
                selected.add(user);
                dfs(bans, selected, depth + 1, combinations);
                selected.remove(user);
            }
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                        new String[]{"fr*d*", "abc1**"},
                        2
                ),
                Arguments.of(
                        new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                        new String[]{"*rodo", "*rodo", "******"},
                        2
                ),
                Arguments.of(
                        new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                        new String[]{"fr*d*", "*rodo", "******", "******"},
                        3
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] user_id, String[] banned_id, int expected) {
        assertThat(new Solution1().solution(user_id, banned_id)).isEqualTo(expected);
    }
}
