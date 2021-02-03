package ps.programmers.exercise;

import java.util.Arrays;

/**
 * 땅따먹기
 * https://programmers.co.kr/learn/courses/30/lessons/12913
 * DP
 */
public class Q12913_DP {

    int solution(int[][] land) {
        for (int i = 1; i < land.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    land[i][j] += Math.max(Math.max(land[i - 1][1], land[i - 1][2]), land[i - 1][3]);
                }
                if (j == 1) {
                    land[i][j] += Math.max(Math.max(land[i - 1][0], land[i - 1][2]), land[i - 1][3]);
                }
                if (j == 2) {
                    land[i][j] += Math.max(Math.max(land[i - 1][0], land[i - 1][1]), land[i - 1][3]);
                }
                if (j == 3) {
                    land[i][j] += Math.max(Math.max(land[i - 1][0], land[i - 1][1]), land[i - 1][2]);
                }
            }
        }

        return Arrays.stream(land[land.length - 1]).max().orElse(0);
    }

    public static void main(String[] args) {
        int[][] land = {
                {1,2,3,5},
                {5,6,7,8},
                {4,3,2,1},
               };
        System.out.println(new Q12913_DP().solution(land));

        land = new int[][]{{4, 3, 2, 1}, {2, 2, 2, 1},{6, 6, 6, 4}, {8, 7, 6, 5}};
        System.out.println(new Q12913_DP().solution(land));

    }
}
