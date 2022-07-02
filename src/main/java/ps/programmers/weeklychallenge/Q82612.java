package ps.programmers.weeklychallenge;

/**
 * 부족한 금액 계산하기
 * https://programmers.co.kr/learn/courses/30/lessons/82612
 * 1. count 많큼 곱하면서 합을 구한다.
 * 2. 합 < money : 0, 합 >= money : 합 - money
 */
public class Q82612 {

    public long solution(int price, int money, int count) {
        int rideMoney = 0;
        for (int i = 1; i <= count; i++) {
            rideMoney += price * i;
        }

        return rideMoney < money ?
                0 :
                rideMoney - money;
    }

    public static void main(String[] args) {
        long solution = new Q82612().solution(3, 31, 4);
        System.out.println(solution);
    }
}
