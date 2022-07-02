package ps.programmers.month_code_challenge1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 두 개 뽑아서 더하기
 * https://programmers.co.kr/learn/courses/30/lessons/68644
 */
public class Q68644 {

    public int[] solution(int[] numbers) {
        Set<Integer> result = new HashSet<>();
        int len = numbers.length;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (i != j) {
                    result.add(numbers[i] + numbers[j]);
                }
            }
        }

        return result.stream().mapToInt(x -> x).sorted().toArray();
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{2, 1, 3, 4, 1};
        System.out.println(Arrays.toString(new Q68644().solution(numbers)));

        numbers = new int[]{5, 0, 2, 7};
        System.out.println(Arrays.toString(new Q68644().solution(numbers)));
    }
}
