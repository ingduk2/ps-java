package ps.programmers.month_code_challenge3;

/**
 * 없는 숫자 더하기
 * https://programmers.co.kr/learn/courses/30/lessons/86051
 */
public class Q86051 {

    private static int CNT = 10;

    public int solution(int[] numbers) {
        int answer = 0;
        boolean[] check = new boolean[CNT];
        for (int number : numbers) {
            check[number] = true;
        }

        for (int i = 0; i < CNT; i++) {
            if (!check[i]) answer += i;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] numbers = {1,2,3,4,6,7,8,0};
        System.out.println(new Q86051().solution(numbers));

        numbers = new int[]{5, 8, 4, 0, 6, 7, 9};
        System.out.println(new Q86051().solution(numbers));
    }

}
