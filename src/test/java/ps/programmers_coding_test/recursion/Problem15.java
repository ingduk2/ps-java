package ps.programmers_coding_test.recursion;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 15 - 쿼드 압축 후 개수 세기 - Level 2

풀이
- 상태
  - (offsetX, offsetY, size): 좌표 (offsetX, offsetY) 에서 시작하여
  - 가로 길이와 세로 길이가 size 인 정사각형을 압축했을 때 남아 있는 0과 1의 개수
- 종료 조건
  - (offsetX, offsetY, size) = 모든 원소가 0, 모든 원소가 1
- 점화식
  - (offsetX, offsetY, size) =
  - (offsetX, offsetY, size/2) +
  - (offsetX + size/2 offsetY, size/2)
  - (offsetX, offsetY + size/2, size/2)
  - (offsetX + size/2, offsetY + size/2, size/2)
 */
public class Problem15 {

    static class Solution1 {

        private static class Count {
            public final int zero;
            public final int one;

            private Count(int zero, int one) {
                this.zero = zero;
                this.one = one;
            }

            public Count add(Count other) {
                return new Count(zero + other.zero, one + other.one);
            }
        }

        public int[] solution(int[][] arr) {
            Count count = count(0, 0, arr.length, arr);
            return new int[]{count.zero, count.one};
        }

        private Count count(int offsetX, int offsetY, int size, int[][] arr) {
            int h = size / 2;
            for (int x = offsetX; x < offsetX + size; x++) {
                for (int y = offsetY; y < offsetY + size; y++) {
                    if (arr[y][x] != arr[offsetY][offsetX]) {
                        return count(offsetX, offsetY, h, arr)
                                .add(count(offsetX + h, offsetY, h, arr))
                                .add(count(offsetX, offsetY + h, h, arr))
                                .add(count(offsetX + h, offsetY + h, h, arr));
                    }
                }
            }

            if (arr[offsetY][offsetX] == 1) {
                return new Count(0, 1);
            }
            return new Count(1, 0);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int[][]{
                        {1,1,0,0},
                        {1,0,0,0},
                        {1,0,0,1},
                        {1,1,1,1}
                        },
                        new int[]{4, 9}
                ),
                Arguments.of(new int[][]{
                        {1,1,1,1,1,1,1,1},
                        {0,1,1,1,1,1,1,1},
                        {0,0,0,0,1,1,1,1},
                        {0,1,0,0,1,1,1,1},
                        {0,0,0,0,0,0,1,1},
                        {0,0,0,0,0,0,0,1},
                        {0,0,0,0,1,0,0,1},
                        {0,0,0,0,1,1,1,1}
                        },
                        new int[]{10, 15}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[][] arr, int[] expected) {
        assertThat(new Solution1().solution(arr)).isEqualTo(expected);
    }
}
