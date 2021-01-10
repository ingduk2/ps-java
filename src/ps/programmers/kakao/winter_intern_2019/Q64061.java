package ps.programmers.kakao.winter_intern_2019;

import java.util.Stack;

/**
 * 크레인 인형뽑기 게임
 * https://programmers.co.kr/learn/courses/30/lessons/64061
 */
public class Q64061 {

    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        Stack<Integer> basckets = new Stack<>();

        for (int i = 0; i < moves.length; i++) {
            int num = moves[i] - 1;
            for (int boardIdx = 0; boardIdx < board.length; boardIdx++) {
                int newDoll = board[boardIdx][num];
                if (newDoll != 0) {

                    if (basckets.size() != 0) {
                        int prevDoll = basckets.peek();
                        if (prevDoll == newDoll) {
                            basckets.pop();
                            answer += 2;
                        } else {
                            basckets.push(newDoll);
                        }
                    } else {
                        basckets.push(newDoll);
                    }

                    board[boardIdx][num] = 0;
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] board = {
                {0,0,0,0,0},
                {0,0,1,0,3},
                {0,2,5,0,1},
                {4,2,4,4,2},
                {3,5,1,3,1},
        };
        int[] moves = {1,5,3,5,1,2,1,4};
        System.out.println(new Q64061().solution(board, moves)); //4
    }
}
