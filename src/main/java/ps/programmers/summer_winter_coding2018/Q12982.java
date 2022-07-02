package ps.programmers.summer_winter_coding2018;

import java.util.Arrays;

/**
 * 예산
 * https://programmers.co.kr/learn/courses/30/lessons/12982
 */
public class Q12982 {

    public int solution(int[] d, int budget) {
        int answer = 0;

        Arrays.sort(d);
        for (int i : d) {
            if (budget - i >= 0) {
                budget -= i;
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] d = {1,3,2,5,4};
        int budget = 9;
        System.out.println(new Q12982().solution(d, budget));

        d = new int[]{2, 2, 3, 3};
        budget = 10;
        System.out.println(new Q12982().solution(d, budget));
    }
}
