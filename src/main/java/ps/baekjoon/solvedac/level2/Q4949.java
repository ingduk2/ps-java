package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 균형잡힌 세상
 * https://www.acmicpc.net/problem/4949
 *
 * 스택 괄호체크.
 *
 * ), ] 닫을때 짝 없으면 push 해줘야하는데 안해서 틀렸음.
 */
public class Q4949 {

    private static boolean checkBalance(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '[') {
                stack.add(c);
            } else if (c == ')' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }

                Character peek = stack.peek();
                if (peek == '(' && c == ')') {
                    stack.pop();
                } else if (peek == '[' && c == ']') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String str = br.readLine();
            if (str.equals(".")) break;

            if (!str.contains("(") && !str.contains(")") && !str.contains("[") && !str.contains("]")) {
                System.out.println("yes");
            } else {
                if (checkBalance(str)) {
                    System.out.println("yes");
                } else {
                    System.out.println("no");
                }
            }
        }
    }

}
