package ps.programmers.exercise.level1;

import java.util.Arrays;

/**
 * 평균 구하기
 * https://programmers.co.kr/learn/courses/30/lessons/12944
 */
public class Q12944 {

    public double solution(int[] arr) {
        int sum = Arrays.stream(arr).sum();
        int cnt = arr.length;

        return (double) sum / cnt;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        System.out.println(new Q12944().solution(arr));
    }
}
