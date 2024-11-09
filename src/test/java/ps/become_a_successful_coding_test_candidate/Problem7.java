package ps.become_a_successful_coding_test_candidate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 07 방문 길이

U, D, R, L 이동, 좌표평면 (0, 0) 위치에서 시작
경계는 (-5, 5) (-5, -5) (5, 5) (5, -5)
캐릭터가 처음 걸어본 길의 길이
좌표평면의 경계를 넘어가는 명령어는 무시

풀이
- 명령어 파싱
- 이동이 가능한지
-- 가능하면 이동, set 에 저장 (a->b, b->a 둘 다 저장)
-- 불가능하면 다음 명령어
 */
public class Problem7 {

    static class Solution1 {
        public int solution(String dirs) {
            Map<Character, int[]> map = new HashMap<>();
            map.put('U', new int[]{0, 1});
            map.put('D', new int[]{0, -1});
            map.put('R', new int[]{1, 0});
            map.put('L', new int[]{-1, 0});

            int x = 0;
            int y = 0;
            Set<String> result = new HashSet<>();

            char[] commands = dirs.toCharArray();
            for (char command : commands) {
                int[] move = map.get(command);
                int nx = x + move[0];
                int ny = y + move[1];

                if (isMove(nx, ny)) {
                    result.add(x + "/" + y + "/" + nx + "/" + ny);
                    result.add(nx + "/" + ny + "/" + x + "/" + y);
                    x = nx;
                    y = ny;
                }
            }

            return result.size() / 2;
        }

        private boolean isMove(int nx, int ny) {
            return -5 <= nx && nx <= 5 && -5 <= ny && ny <= 5;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("ULURRDLLU", 7),
                Arguments.of("LULLLLLLU", 7)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String dirs, int expected) {
        int result = new Solution1().solution(dirs);
        assertThat(result).isEqualTo(expected);
    }
}
