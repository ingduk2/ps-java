package ps.programmers.exercise;

import java.util.Arrays;

/**
 * 숫자의 표현
 * https://programmers.co.kr/learn/courses/30/lessons/12924
 */
public class Q12924 {

//            1 + 2 + 3 + 4 + 5 = 15
//            4 + 5 + 6 = 15
//            7 + 8 = 15
//            15 = 15
    public int solution(int n) {
        int answer = 0;

        for (int i = 1; i < n + 1; i++) {
            int sum = 0;
            for (int j = i; j < n + 1; j++) {
                sum += j;
                if (sum == n) {
                    answer += 1;
                    break;
                }
                else if (sum > n) break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Q12924().solution(15));
        System.out.println(new Q12924().solution(1));
    }
}
