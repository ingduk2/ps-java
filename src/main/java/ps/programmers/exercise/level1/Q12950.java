package ps.programmers.exercise.level1;

import java.util.Arrays;

/**
 * 행렬의 덧셈
 * https://programmers.co.kr/learn/courses/30/lessons/12950
 */
public class Q12950 {

    public int[][] solution(int[][] arr1, int[][] arr2) {
        int x = arr1.length;
        int y = arr1[0].length;
        int[][] answer = new int[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                answer[i][j] += arr1[i][j] + arr2[i][j];
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] arr1 = {{1,2},{2,3}};
        int[][] arr2 = {{3,4},{5,6}};//	[[4,6],[7,9]]
        System.out.println(new Q12950().solution(arr1, arr2));

        arr1 = new int[][]{{1},{2}};
        arr2 = new int[][]{{3},{4}};//	[[4],[6]]
        System.out.println(new Q12950().solution(arr1, arr2));
    }
}
