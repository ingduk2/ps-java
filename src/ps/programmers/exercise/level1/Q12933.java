package ps.programmers.exercise.level1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 정수 내림차순으로 배치하기
 * https://programmers.co.kr/learn/courses/30/lessons/12933
 *
 */
public class Q12933 {

    public long solution(long n) {
        List<Integer> numList = new ArrayList<>();
        while (n > 0) {
            int remain = (int) (n % 10);
            n /= 10;

            numList.add(remain);
        }

        String joinStr = numList.stream()
                .sorted(Comparator.reverseOrder())
                .map(i -> Integer.toString(i))
                .collect(Collectors.joining());

        return Long.parseLong(joinStr);
    }

    public static void main(String[] args) {
        System.out.println(new Q12933().solution(118372));
    }
}
