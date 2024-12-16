package ps.programmers_coding_test.array;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 2 삼각 달팽이 - Level 2

풀이
- 1. n * n 2차원 배열 선언
- 2. 숫자를 채울 현재 위치를 (0, 0) 으로 설정
- 3. 방향에 따라 이동할 수 없을 때까지 반복하면서 숫자 채우기
-- 아래로 이동하면서 숫자 채우기
-- 오른쪽으로 이동하면서 숫자 채우기
-- 왼쪽 위로 이동하면서 숫자 채우기
- 4. 채워진 숫자를 차례대로 1차원 배열에 옮겨서 반환
 */
public class Problem2 {

    static class Solution1 {
        private static final int[] dx = {0, 1, -1};
        private static final int[] dy = {1, 0, -1};

        public int[] solution(int n) {
            int[][] arr = new int[n][n];

            int num = 1;
            int x = 0;
            int y = 0;
            int d = 0;

            while (true) {
                arr[y][x] = num++;
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (isEnd(n, nx, ny, arr)) {
                    d = (d + 1) % 3;
                    nx = x + dx[d];
                    ny = y + dy[d];

                    if (isEnd(n, nx, ny, arr)) {
                        break;
                    }
                }

                x = nx;
                y = ny;
            }

            int[] answer = new int[num - 1];
            int index = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    answer[index++] = arr[i][j];
                }
            }

            return answer;
        }

        private boolean isEnd(int n, int nx, int ny, int[][] arr) {
            return nx == n || ny == n || nx == -1 || ny == -1 || arr[ny][nx] != 0;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        4,
                        new int[]{1, 2, 9, 3, 10, 8, 4, 5, 6, 7}
                ),
                Arguments.of(
                        5,
                        new int[]{1, 2, 12, 3, 13, 11, 4, 14, 15, 10, 5, 6, 7, 8, 9}
                ),
                Arguments.of(
                        6,
                        new int[]{1, 2, 15, 3, 16, 14, 4, 17, 21, 13, 5, 18, 19, 20, 12, 6, 7, 8, 9, 10, 11}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int n, int[] expected) {
        assertThat(new Solution1().solution(n)).isEqualTo(expected);
    }
}
