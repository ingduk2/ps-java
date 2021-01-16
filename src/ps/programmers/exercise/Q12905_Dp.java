package ps.programmers.exercise;

import java.util.Arrays;

/**
 * 가장 큰 정사각형 찾기
 * dp 문제..
 * https://programmers.co.kr/learn/courses/30/lessons/12905
 *
 */
public class Q12905_Dp {
    public int solution(int [][]board)
    {

        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    board[i][j] = Math.min(Math.min(board[i - 1][j], board[i][j - 1]), board[i - 1][j - 1]) + 1;
                }
            }
        }

        int max = Arrays.stream(board).flatMapToInt(Arrays::stream).max().orElse(0);

        return (int) Math.pow(max, 2);
    }

    public static void main(String[] args) {
        int[][] board = {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}};
        System.out.println(new Q12905_Dp().solution(board));

        board = new int[][]{{0, 0, 1, 1}, {1, 1, 1, 1}};
        System.out.println(new Q12905_Dp().solution(board));

        board = new int[][]{{0, 1}};
        System.out.println(new Q12905_Dp().solution(board));
    }
}
