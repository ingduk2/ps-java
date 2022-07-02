package ps.programmers.kakao.blind2018;

import java.util.HashSet;
import java.util.Set;

/**
 * [1차] 프렌즈4블록
 * https://programmers.co.kr/learn/courses/30/lessons/17679
 *
 * 1. 사각형 모양으로 순회.
 * 2. 4개가 다 같으면 check 배열에 1 세팅.
 * 3. check배열에서 1인 경우 board배열에서 위에있는 블록들을 아래로 내린다.(기존블록은 삭제)
 * 4. 1, 2, 3번 반복.
 * 5. check배열에서 1이었던 경우의 총합이 삭제된 블록.
 */
public class Q17679 {

    private int[][] check;
    private int answer;

    public int solution(int m, int n, String[] board) {
        //0 블록 있음. 1 블록 없음
        check = new int[m][n];

        char[][] gameBoard = new char[m][n];
        for (int i = 0; i < m; i++) {
            String s = board[i];
            for (int j = 0; j < n; j++) {
                gameBoard[i][j] = s.charAt(j);
            }
        }

        //1 순회 4개씩.
        while (search(gameBoard, m, n)) {
            remove(gameBoard, m, n);
        }

        return answer;
    }

    private void remove(char[][] gameBoard, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (check[i][j] == 1) {

                    gameBoard[i][j] = ' ';
                    if (i > 0) {
                        for (int k = i; k > 0; k--) {
                            gameBoard[k][j] = gameBoard[k - 1][j];
                            gameBoard[k - 1][j] = ' ';
                        }
                    }

                    answer++;
                    check[i][j] = 0;
                }
            }
        }
    }

    private boolean isRemoveBlock(String square) {
        if (square.contains(" ")) {
            return false;
        }

        Set<Character> blockSet = new HashSet<>();
        for (int i = 0; i < square.length(); i++) {
            blockSet.add(square.charAt(i));
        }

        return blockSet.size() == 1;
    }

    private boolean search(char[][] gameBoard, int m, int n) {
        boolean isSearch = false;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {

                String square = new StringBuilder()
                        .append(gameBoard[i][j])
                        .append(gameBoard[i][j + 1])
                        .append(gameBoard[i + 1][j])
                        .append(gameBoard[i + 1][j + 1])
                        .toString();

                if (isRemoveBlock(square)) {
                    isSearch = true;
                    check[i][j] = 1;
                    check[i][j + 1] = 1;
                    check[i + 1][j] = 1;
                    check[i + 1][j + 1] = 1;
                }
            }
        }
        return isSearch;
    }

    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};// ,	14
        System.out.println(new Q17679().solution(m, n, board));

        m = 6;
        n = 6;
        board = new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};//	15
        System.out.println(new Q17679().solution(m, n, board));
    }
}
