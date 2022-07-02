package ps.programmers.tipsdown2017;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 짝지어 제거하기
 * https://programmers.co.kr/learn/courses/30/lessons/12973
 *
 * 무식하게 풀면 효율성에서 떨어짐..
 * 스택을 사용해아한다고함. 괄호문제랑 비슷한듯 싶음
 */
public class Q12973 {

    public int solutionFailEfficiency(String s) {
        List<String> strList = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            strList.add(String.valueOf(s.charAt(i)));
        }

        int idx = 0;
        while(true) {

            if (strList.isEmpty()) return 1;

            if (strList.size() - 1 == idx) return 0;

            String s1 = strList.get(idx);
            String s2 = strList.get(idx + 1);

            if (s1.equals(s2)) {
                strList.remove(idx--);
                strList.remove(idx + 1);
                idx = 0;
                continue;
            }

            idx ++;
        }
    }

    public int solution(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.empty()) {
                stack.push(c);
            } else {
                if (stack.peek() == c) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        if (stack.size() == 0) {
            return 1;
        } else {
            return 0;
        }
    }



    public static void main(String[] args) {
//        System.out.println(new Q12973().solutionFailEfficiency("baabaa"));
//        System.out.println(new Q12973().solutionFailEfficiency("cdcd"));
//        System.out.println(new Q12973().solutionFailEfficiency("baaba"));


        System.out.println(new Q12973().solution("baabaa"));
        System.out.println(new Q12973().solution("cdcd"));
        System.out.println(new Q12973().solution("baaba"));

    }
}
