package ps.programmers.month_code_challenge1;

import java.util.Arrays;

/**
 * 쿼드압축 후 개수 세기
 * https://programmers.co.kr/learn/courses/30/lessons/68936
 */
public class Q68936 {

    private int getSquareSum(int i, int j, int plus, int[][] arr, int[][] compressArr) {
        int sum = 0;
        for (int x = i; x < plus + i; x++) {
            for (int y = j; y < plus + j; y++) {
                if (compressArr[x][y] == 1) return -1;
                sum += arr[x][y];
            }
        }
        return sum;
    }

    private void fillCompressArr(int i, int j, int plus, int[][] compressArr) {
        for (int x = i; x < plus + i; x++) {
            for (int y = j; y < plus + j; y++) {
                compressArr[x][y] = 1;
            }
        }
    }

    public int[] solution(int[][] arr) {

        // 4, 4^2, 4^3
        // 1, 2, 4, 8
        int len = arr.length;

        int zeroCnt = 0;
        int oneCnt = 0;
        int[][] compressArr = new int[len][len];

        int plus = len;
        while (plus > 0) {
            for (int i = 0; i < len; i+= plus) {
                for (int j = 0; j < len; j+= plus) {

                    if (plus > 1) {
                        int sum = getSquareSum(i, j, plus, arr, compressArr);

                        if (sum == 0) {
                            zeroCnt++;
                            fillCompressArr(i, j, plus, compressArr);
                        }

                        if (sum == plus * plus) {
                            oneCnt++;
                            fillCompressArr(i, j, plus, compressArr);
                        }
                    }

                }
            }
            plus /= 2;
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (compressArr[i][j] == 0) {
                    if (arr[i][j] == 1) {
                        oneCnt++;
                    } else {
                        zeroCnt++;
                    }
                }
            }
        }

        return new int[]{zeroCnt, oneCnt};
    }


    public static void main(String[] args) {

        int[][] arr = new int[][]
                {
                        {0}
                };
        System.out.println(Arrays.toString(new Q68936().solution(arr)));

        arr = new int[][]
                {
                        {1,1},
                        {1,1},

                };
        System.out.println(Arrays.toString(new Q68936().solution(arr)));

        arr = new int[][]
                {
                        {1,1,0,0},
                        {1,0,0,0},
                        {1,0,0,1},
                        {1,1,1,1}
                };
        System.out.println(Arrays.toString(new Q68936().solution(arr)));

        arr = new int[][]
                {
                        {1,1,1,1,1,1,1,1},
                        {0,1,1,1,1,1,1,1},
                        {0,0,0,0,1,1,1,1},
                        {0,1,0,0,1,1,1,1},
                        {0,0,0,0,0,0,1,1},
                        {0,0,0,0,0,0,0,1},
                        {0,0,0,0,1,0,0,1},
                        {0,0,0,0,1,1,1,1}
                };
        System.out.println(Arrays.toString(new Q68936().solution(arr)));

    }
}
