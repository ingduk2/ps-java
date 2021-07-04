package ps.programmers.dev_matching_2021;

import java.util.Arrays;

/**
 * 로또의 최고 순위와 최저 순위
 * https://programmers.co.kr/learn/courses/30/lessons/77484
 * <p>
 * 0을 뺀 개수가 몇개나 맞는지 구함.
 * 개수 + 0 개수 = 최고등수
 * 개수 = 최저등
 */
public class Q77484 {
    private static int length = 6;
    private static int[] rank = {6, 6, 5, 4, 3, 2, 1};

    private static int getZeroCount(int[] lottos) {
        int cnt = 0;
        for (int i = 0; i < length; i++) {
            if (lottos[i] == 0) cnt++;
        }
        return cnt;
    }

    private static int getSameCount(int[] lottos, int[] win_nums) {
        int cnt = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (lottos[i] == win_nums[j]) {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }

    public int[] solution(int[] lottos, int[] win_nums) {
        int zeroCount = getZeroCount(lottos);
        int sameCount = getSameCount(lottos, win_nums);

        return new int[]{rank[sameCount + zeroCount], rank[sameCount]};
    }

    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19}; //	[3, 5]
        System.out.println(Arrays.toString(new Q77484().solution(lottos, win_nums)));

        lottos = new int[]{0, 0, 0, 0, 0, 0};
        win_nums = new int[]{38, 19, 20, 40, 15, 25};    //[1, 6]
        System.out.println(Arrays.toString(new Q77484().solution(lottos, win_nums)));

        lottos = new int[]{45, 4, 35, 20, 3, 9};
        win_nums = new int[]{20, 9, 3, 45, 4, 35}; //	[1, 1]
        System.out.println(Arrays.toString(new Q77484().solution(lottos, win_nums)));
    }
}
