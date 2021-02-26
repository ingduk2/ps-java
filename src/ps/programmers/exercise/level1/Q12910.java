package ps.programmers.exercise.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 나누어 떨어지는 숫자 배열
 * https://programmers.co.kr/learn/courses/30/lessons/12910
 */
public class Q12910 {

    public int[] solution(int[] arr, int divisor) {
        List<Integer> result = new ArrayList<>();

        for (int a : arr) {
            if (a % divisor == 0) result.add(a);
        }

        if (result.size() == 0) return new int[]{-1};

        return result.stream().sorted().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] arr = {5, 9, 7, 10};
        int divisor = 5; //	[5, 10]
        System.out.println(Arrays.toString(new Q12910().solution(arr, divisor)));

        arr = new int[]{2, 36, 1, 3};
        divisor = 1; //	[1, 2, 3, 36]
        System.out.println(Arrays.toString(new Q12910().solution(arr, divisor)));

        arr = new int[]{3,2,6};
        divisor = 10; //	[-1]
        System.out.println(Arrays.toString(new Q12910().solution(arr, divisor)));
    }
}
