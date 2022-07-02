package ps.programmers.dev_matching_2021;

import java.util.Arrays;

/**
 * 행렬 테두리 회전하기
 * https://programmers.co.kr/learn/courses/30/lessons/77485?language=java
 *
 * 1. rows, columns 로 arr[][] 생성
 * 2. query 당 행렬 돌리기()
 *         // 2, 2, 5, 4
 *         // 2,2 2,3 2,4
 *         // 3,2     3,4
 *         // 4,2     4,4
 *         // 5,2 5,3 5,4
 *
 *  x1, y1 - 1 시작.(오른쪽으로 시작)
 *  len = 1
 *  for 2번
 *      for 오른쪽, 왼쪽 (y2 - y1 + len)
 *      처음 오른쪽으로 시작시 y2 - y1 + 1
 *      그다음에 왼쪽으로 y2 - y1
 *
 *      len --
 *
 *      for 아래, 위
 *      처음 아래로 시작시 x2 - x1
 *      마지막 위로 이동시 x2 - x1 - 1
 */
public class Q77485 {
    
    private int[][] matrix;

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        fillMatrix(rows, columns);

        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotateMatrixAndGetMinNum(queries[i]);
        }

        return answer;
    }

    private int rotateMatrixAndGetMinNum(int[] query) {
        int x1 = query[0];
        int y1 = query[1];
        int x2 = query[2];
        int y2 = query[3];

        int nx = x1;
        int ny = y1 - 1;
        int len = 1;
        int move = 1;
        int saveNum = matrix[x1][y1];
        int minNum = matrix[x1][y1];
        for (int loop = 0; loop < 2; loop++) {
            for (int i = 0; i < y2 - y1 + len; i++) {
                ny += move;
                minNum = Math.min(minNum, matrix[nx][ny]);
                saveNum = shiftNum(saveNum, nx, ny);
            }
            len--;

            for (int i = 0; i < x2 - x1 + len; i++) {
                nx += move;
                minNum = Math.min(minNum, matrix[nx][ny]);
                saveNum = shiftNum(saveNum, nx, ny);
            }
            move *= -1;
        }

        matrix[x1][y1] = saveNum;
        return minNum;
    }

    private int shiftNum(int saveNum, int nx, int ny) {
        if (saveNum != matrix[nx][ny]) {
            int current = matrix[nx][ny];
            matrix[nx][ny] = saveNum;
            saveNum = current;
        }
        return saveNum;
    }

    private void printMatrix() {
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    private void fillMatrix(int rows, int columns) {
        matrix = new int[rows + 1][columns + 1];
        int num = 1;
        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < columns + 1; j++) {
                matrix[i][j] = num++;
            }
        }
    }

    public static void main(String[] args) {
        int[] solution = new Q77485().solution(6, 6,
                new int[][]{
                        {2, 2, 5, 4},
                        {3, 3, 6, 6},
                        {5, 1, 6, 3}});
        System.out.println(Arrays.toString(solution));

        solution = new Q77485().solution(3, 3,
                new int[][]{
                        {1, 1, 2, 2},
                        {1, 2, 2, 3},
                        {2, 1, 3, 2},
                        {2, 2, 3, 3}});
        System.out.println(Arrays.toString(solution));

        solution = new Q77485().solution(100, 97,
                new int[][]{
                        {1, 1, 100, 97}});
        System.out.println(Arrays.toString(solution));
    }
}
