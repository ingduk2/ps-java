package ps.baekjoon.solvedac.level3;

import java.util.Scanner;

/**
 * 잃어버린 괄호
 * https://www.acmicpc.net/problem/1541
 *
 * 55-50+40
 * +로 큰 수들을 가로로 묶으면 최소값이 된다.
 *
 * 먼저 - 로 parse 그럼 +만 남음.
 * 그다음에 + parse 해서 계산
 * 남은 수들을 -로 계산
 * 10-20-30+40-50+60
 * 10-20-(30+40)-(50+60)
 *
 * 0번째 미리 계산하면
 * 10+10 나오는경우에 - parse 안되서 NumberFormatException 발생
 *
 * min을 0으로나 -1로 했더니 틀림.
 * 0일 경우에는 처음에 0이 나오고 다음에 다른수가 나오면 꼬임.
 * -1 인 경우에도 1-2-2 같은 경우 2가 나와버림..꼬임,. 1 -> -1 -> 2대입.
 *
 */
public class Q1541 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        String[] expression = str.split("-");

        int min = Integer.MAX_VALUE;
        for (String s : expression) {
            String[] numbers = s.split("\\+");
            int sum = 0;
            for (String number : numbers) {
                sum += Integer.parseInt(number);
            }

            if (min == Integer.MAX_VALUE) {
                min = sum;
            } else {
                min -= sum;
            }
        }

        System.out.println(min);
    }
}
