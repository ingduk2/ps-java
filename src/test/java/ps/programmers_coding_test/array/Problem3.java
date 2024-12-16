package ps.programmers_coding_test.array;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 3 거리두기 확인하기 - Level 2

풀이
- 1. 모든 응시자에 대해서 맨해튼거리 2 확인
-- 중간에 지키지 않았으면 0
- 2. 모든 응시자가 지켰으면 1
 */
public class Problem3 {

    static class Solution1 {
        private static final int[] dx = {0, -1, 1, 0};
        private static final int[] dy = {1, 0, 0, -1};

        public int[] solution(String[][] places) {
            int[] answer = new int[places.length];
            for (int idx = 0; idx < places.length; idx++) {
                String[] place = places[idx];
                char[][] room = new char[5][5];
                for (int j = 0; j < places.length; j++) {
                    for (int k = 0; k < places.length; k++) {
                        room[j][k] = place[j].charAt(k);
                    }
                }

                answer[idx] = isDistanced(room) ? 1 : 0;
            }

            return answer;
        }

        private boolean isDistanced(char[][] room) {
            for (int y = 0; y < room.length; y++) {
                for (int x = 0; x < room[y].length; x++) {
                    if (room[y][x] != 'P') continue;

                    for (int k = 0; k < 4; k++) {
                        int ny = y + dy[k];
                        int nx = x + dx[k];

                        if (ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length) {
                            continue;
                        }

                        if (room[ny][nx] == 'P') {
                            return false;
                        } else if (room[ny][nx] == 'O') {
                            if (isNextToVolunteer(room, ny, nx, 3 - k)) {
                                return false;
                            }
                        }
                    }
                }
            }

            return true;
        }

        private boolean isNextToVolunteer(char[][] room, int y, int x, int exclude) {
            for (int i = 0; i < 4; i++) {
                if (i == exclude) continue;

                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length) {
                    continue;
                }

                if (room[ny][nx] == 'P') {
                    return true;
                }
            }

            return false;
        }
    }

    static class Solution2 {
        private static final int[] dx = {0, -1, 1, 0};
        private static final int[] dy = {1, 0, 0, -1};

        public int[] solution(String[][] places) {
            int len = places.length;
            int[] answer = new int[len];
            for (int i = 0; i < len; i++) {
                String[] place = places[i];
                char[][] room = new char[5][5];
                for (int j = 0; j < len; j++) {
                    room[j] = place[j].toCharArray();
                }

                answer[i] = isDistanceKept(room) ? 1 : 0;
            }

            return answer;
        }

        private boolean isDistanceKept(char[][] room) {
            int len = room.length;
            for (int y = 0; y < len; y++) {
                for (int x = 0; x < len; x++) {
                    if (room[y][x] == 'P') {
                        if (!dfs(room, y, x, 0, new boolean[len][len])) {
                            return false;
                        }
                    }
                }
            }

            return true;
        }

        private boolean dfs(char[][] room, int y, int x, int depth, boolean[][] visited) {
            if (depth > 2) return true;
            if (depth > 0 && room[y][x] == 'P') return false;

            visited[y][x] = true;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length) {
                    continue;
                }

                if (visited[ny][nx]) {
                    continue;
                }

                if (room[ny][nx] != 'X') {
                    if (!dfs(room, ny, nx, depth + 1, visited)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[][]{
                                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
                        },
                        new int[]{1, 0, 1, 1, 1}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[][] places, int[] expected) {
        assertThat(new Solution1().solution(places)).isEqualTo(expected);
        assertThat(new Solution2().solution(places)).isEqualTo(expected);
    }
}
