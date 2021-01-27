package ps.programmers.exercise;

import java.util.Arrays;

/**
 * 최솟값 만들기
 * https://programmers.co.kr/learn/courses/30/lessons/12941
 */
public class Q12941 {

    public int solution(int []A, int []B)
    {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0; i < A.length; i++) {
            answer += A[i] * B[A.length - 1 - i];
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] A = {1, 4, 2};
        int[] B = {5, 4, 4};
        System.out.println(new Q12941().solution(A, B));

        A = new int[]{1, 2};
        B = new int[]{3, 4};
        System.out.println(new Q12941().solution(A, B));
    }
}
