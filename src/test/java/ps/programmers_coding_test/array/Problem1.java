package ps.programmers_coding_test.array;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 1 교점에 별 만들기 - Level 2

풀이
- 1. 모든 직성 쌍에 대해 반복
-- 교점 좌표 구하기
-- 정수 좌표만 저장
- 2. 저장된 정수들에 대해 x, y 좌표의 최댓값, 최솟값 구하기
- 3. 구한 최댓값, 최솟값을 이용하여 2차원 배열의 크기 결정
- 4. 2차원 배열에 별 표시
- 5. 문자열 배열로 변환 후 반환
 */
public class Problem1 {

    static class Solution1 {

        private static class Point {
            private final long x;
            private final long y;

            public Point(long x, long y) {
                this.x = x;
                this.y = y;
            }
        }

        public String[] solution(int[][] line) {
            List<Point> points = new ArrayList<>();
            for (int i = 0; i < line.length; i++) {
                for (int j = i + 1; j < line.length; j++) {
                    Point point = intersection(
                            line[i][0], line[i][1], line[i][2],
                            line[j][0], line[j][1], line[j][2]
                    );

                    if (point != null) {
                        points.add(point);
                    }
                }
            }

            Point min = getMinimumPoint(points);
            Point max = getMaximumPoint(points);

            int width = (int) (max.x - min.x + 1);
            int height = (int) (max.y - min.y + 1);

            char[][] arr = new char[height][width];
            for (char[] row : arr) {
                Arrays.fill(row, '.');
            }

            for (Point point : points) {
                int x = (int) (point.x - min.x);
                int y = (int) (max.y - point.y);
                arr[y][x] = '*';
            }

            String[] answer = new String[arr.length];
            for (int i = 0; i < answer.length; i++) {
                answer[i] = new String(arr[i]);
            }

            return answer;
        }

        private Point intersection(long a1, long b1, long c1, long a2, long b2, long c2) {
            double x = (double) (b1 * c2 - b2 * c1) / (a1 * b2 - a2 * b1);
            double y = (double) (a2 * c1 - a1 * c2) / (a1 * b2 - a2 * b1);

            if (x % 1 != 0 || y % 1 != 0) {
                return null;
            }

            return new Point((long) x, (long) y);
        }

        private Point getMinimumPoint(List<Point> points) {
            long x = Long.MAX_VALUE;
            long y = Long.MAX_VALUE;

            for (Point point : points) {
                x = Math.min(x, point.x);
                y = Math.min(y, point.y);
            }

            return new Point(x, y);
        }

        private Point getMaximumPoint(List<Point> points) {
            long x = Long.MIN_VALUE;
            long y = Long.MIN_VALUE;

            for (Point point : points) {
                x = Math.max(x, point.x);
                y = Math.max(y, point.y);
            }

            return new Point(x, y);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {2, -1, 4},
                                {-2, -1, 4},
                                {0, -1, 1},
                                {5, -8, -12},
                                {5, 8, 12}
                        },
                        new String[]{
                                "....*....",
                                ".........",
                                ".........",
                                "*.......*",
                                ".........",
                                ".........",
                                ".........",
                                ".........",
                                "*.......*"
                        }
                ),
                Arguments.of(
                        new int[][]{
                                {0, 1, -1},
                                {1, 0, -1},
                                {1, 0, 1}
                        },
                        new String[]{
                                "*.*"
                        }
                ),
                Arguments.of(
                        new int[][]{
                                {1, -1, 0},
                                {2, -1, 0}
                        },
                        new String[]{
                                "*"
                        }
                ),
                Arguments.of(
                        new int[][]{
                                {1, -1, 0},
                                {2, -1, 0},
                                {4, -1, 0}
                        },
                        new String[]{
                                "*"
                        }
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[][] line, String[] result) {
        assertThat(new Solution1().solution(line)).isEqualTo(result);
    }
}
