package ps.programmers.exercise.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 제일 작은 수 제거하기
 * https://programmers.co.kr/learn/courses/30/lessons/12935
 */
public class Q12935 {

    public int[] solution(int[] arr) {

        if (arr.length == 1) {
            return new int[]{-1};
        }

        int min = Arrays.stream(arr).min().getAsInt();

        return Arrays.stream(arr).filter(i -> i != min).toArray();
    }

    public static void main(String[] args) {
        int[] arr = {4,3,2,1};//	[4,3,2]
        System.out.println(Arrays.toString(new Q12935().solution(arr)));

        arr = new int[]{10};//	[-1]
        System.out.println(Arrays.toString(new Q12935().solution(arr)));
    }
}
