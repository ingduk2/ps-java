package ps.thiscodingtest.previous_problems.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 모험가 길드
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        int[] array = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        int result = 0;
        int count = 0;

        // 그룹에 포함된 모험가의 수가 현재의 공포도 이상이면 그룹 +
        for (int pear : array) {
            count += 1;
            if (count >= pear) {
                result += 1;
                count = 0;
            }
        }

        System.out.println(result);
    }
}
