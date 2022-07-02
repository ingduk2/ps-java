package ps.programmers.exercise.level1;

import java.util.Arrays;
import java.util.Stack;

/**
 * 같은 숫자는 싫어
 * https://programmers.co.kr/learn/courses/30/lessons/12906
 */
public class Q12906 {

    public int[] solution(int []arr) {

        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);

        for (int i = 0; i < arr.length; i++) {
            if (stack.peek() != arr[i]) {
                stack.push(arr[i]);
            }
        }

        return stack.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
//[1,1,3,3,0,1,1]	[1,3,0,1]
        System.out.println(Arrays.toString(new Q12906().solution(new int[]{1,1,3,3,0,1,1})));
//[4,4,4,3,3]	[4,3]
        System.out.println(Arrays.toString(new Q12906().solution(new int[]{4,4,4,3,3})));
    }
}
