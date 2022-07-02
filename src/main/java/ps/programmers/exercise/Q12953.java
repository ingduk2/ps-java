package ps.programmers.exercise;

/**
 * N개의 최소공배수
 * https://programmers.co.kr/learn/courses/30/lessons/12953
 * 여러개의 경우 연달아서 lcm 구한다.
 */
public class Q12953 {
    private int gcd(int a, int b){
        while (b != 0) {
            int r = a%b;
            a = b;
            b = r;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public int solution(int[] arr) {
        if (arr.length <= 1) {
            return arr[0];
        }

        int answer = lcm(arr[0], arr[1]);

        for (int i = 2; i < arr.length; i++) {
            answer = lcm(answer, arr[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
//        [2,6,8,14]	168
//                [1,2,3]	6

        int[] arr = {2, 6, 8, 14};
        System.out.println(new Q12953().solution(arr));

        arr = new int[]{1, 2, 3};
        System.out.println(new Q12953().solution(arr));
    }
}
