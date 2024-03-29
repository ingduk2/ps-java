package ps.programmers.exercise;

import java.util.Stack;

/**
 * 올바른 괄호
 * https://programmers.co.kr/learn/courses/30/lessons/12909
 */
public class Q12909 {

    boolean solution(String s) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.empty()) {
                stack.push(c);
            } else {
                if (stack.peek() == '(' && c == ')') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.empty();
    }

    boolean solution2(String s) {
        int cnt = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') cnt++;
            if (s.charAt(i) == ')') cnt--;
            if (cnt < 0) break;
        }

        return cnt == 0;
    }

    public static void main(String[] args) {
        /*
        ()()	true
(())()	true
)()(	false
(()(	false
         */

        System.out.println(new Q12909().solution("()()"));
        System.out.println(new Q12909().solution("(())()"));
        System.out.println(new Q12909().solution(")()("));
        System.out.println(new Q12909().solution("(()("));

        System.out.println(new Q12909().solution2("()()"));
        System.out.println(new Q12909().solution2("(())()"));
        System.out.println(new Q12909().solution2(")()("));
        System.out.println(new Q12909().solution2("(()("));

    }
}
