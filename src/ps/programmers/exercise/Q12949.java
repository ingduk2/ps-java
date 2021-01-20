package ps.programmers.exercise;

import java.util.Arrays;

/**
 * 행렬의 곱셉
 * https://programmers.co.kr/learn/courses/30/lessons/12949
 */
public class Q12949 {

    public int[][] solution(int[][] arr1, int[][] arr2) {
        int xLen = arr1.length;
        int yLen = arr2[0].length;
        int[][] answer = new int[xLen][yLen];

        //arr1 x
        for (int i = 0; i < xLen; i++) {
            //arr2 y
            for (int j = 0; j < yLen; j++) {
                //arr2 x
                for (int k = 0; k < arr2.length; k++) {
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] arr1 = {
                {1, 4},
                {3, 2},
                {4, 1},
        };

        int[][] arr2 = {
                {3, 3},
                {3, 3}
        };
        System.out.println(new Q12949().solution(arr1, arr2));


        arr1 = new int[][]{
                {2, 3, 2},
                {4, 2, 4},
                {3, 1, 4}
        };

        arr2 = new int[][]{
                {5, 4, 3},
                {2, 4, 1},
                {3, 1, 1}
        };
        System.out.println(new Q12949().solution(arr1, arr2));


        arr1 = new int[][] {
                {2, 3, 2},
                {4, 2, 4},
                {3, 1, 4}
        };
        arr2 = new int[][] {
                {5, 4},
                {2, 4},
                {3, 1}};
        System.out.println(new Q12949().solution(arr1, arr2));
    }
}
