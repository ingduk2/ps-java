package ps.become_a_successful_coding_test_candidate.backtracking;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 44 스도쿠 퍼즐

풀이
- 조건
-- 1. 행에 넣으려는 숫자가 없을 때
-- 2. 열에 넣으려는 숫자가 없을 때
-- 3. 해당 위치 포함 3 * 3 박스에 넣으려는 숫자가 없을 때
 */
public class Problem44 {

    static class Solution1 {

        private static class Block {
            private final int i;
            private final int j;

            public Block(int i, int j) {
                this.i = i;
                this.j = j;
            }
        }

        private int[][] board;

        public int[][] solution(int[][] board) {
            this.board = board;
            findSolution();
            return board;
        }

        private boolean findSolution() {
            Block emptyBlock = findEmptyPosition();
            if (emptyBlock == null) {
                return true;
            }

            int row = emptyBlock.i;
            int col = emptyBlock.j;

            for (int num = 0; num <= 9; num++) {
                if (isValid(num, row, col)) {
                    board[row][col] = num;

                    if (findSolution()) {
                        return true;
                    }

                    board[row][col] = 0;
                }
            }

            return false;
        }

        private Block findEmptyPosition() {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 0) {
                        return new Block(i, j);
                    }
                }
            }

            return null;
        }

        private boolean isValid(int num, int row, int col) {
            return !inRow(num, row) &&
                    !inCol(num, col) &&
                    !inBox(num, row, col);
        }

        private boolean inRow(int num, int row) {
            return Arrays.stream(board[row]).anyMatch(n -> n == num);
        }

        private boolean inCol(int num, int col) {
            for (int i = 0; i < 9; i++) {
                if (board[i][col] == num) {
                    return true;
                }
            }

            return false;
        }

        private boolean inBox(int num, int row, int col) {
            int boxRow = (row / 3) * 3;
            int boxCol = (col / 3) * 3;

            for (int i = boxRow; i < boxRow + 3; i++) {
                for (int j = boxCol; j < boxCol + 3; j++) {
                    if (board[i][j] == num) {
                        return true;
                    }
                }
            }

            return false;
        }
    }


    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                                {0, 0, 0, 0, 8, 0, 0, 7, 9}
                        },
                        new int[][]{
                                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                                {3, 4, 5, 2, 8, 6, 1, 7, 9}
                        }
                ),
                Arguments.of(
                        new int[][]{
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        },
                        new int[][]{
                                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                                {2, 1, 4, 3, 6, 5, 8, 9, 7},
                                {3, 6, 5, 8, 9, 7, 2, 1, 4},
                                {8, 9, 7, 2, 1, 4, 3, 6, 5},
                                {5, 3, 1, 6, 4, 2, 9, 7, 8},
                                {6, 4, 2, 9, 7, 8, 5, 3, 1},
                                {9, 7, 8, 5, 3, 1, 6, 4, 2},
                        }
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[][] board, int[][] expected) {
        assertThat(new Solution1().solution(board)).isEqualTo(expected);
    }
}
