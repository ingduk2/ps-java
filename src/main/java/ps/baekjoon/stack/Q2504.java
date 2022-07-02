package ps.baekjoon.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 괄호의 값
 * https://www.acmicpc.net/problem/2504
 *
 * 쌍 맞추는것은 스택인듯
 * 가로 닫힐때 계산할려고하니 답이 안나옴....
 *
 * 분배법칙나열 이용해서 2 * (3 + 4 * 5) = 6 + 40 이용해서
 * 가로가 열리면 계산
 *
 * mul = 1
 * 가로가 열리면 ([ -> mul *= (2 or 3)
 * 가로가 닫히면 )] 스택 체크 후 이전 가로와 쌍이 맞으면 (), [] -> result += mul, mul /= (2 or 3)
 *
 * (2 + 3*3)2 + 2*3 = 28
 * (()[[]])([])
 * 0 -> mul = 1 * 2, ret = 0
 * 1 -> mul = 2 * 2, ret = 0
 * 2 -> mul = 4 / 2, ret = 4
 * 3 -> mul = 2 * 3, ret = 4
 * 4 -> mul = 6 * 3, ret = 4
 * 5 -> mul = 18 / 3, ret = 4 + 18
 * 6 -> mul = 6 / 3, ret = 22 (가로가 닫히지만 이전것이랑 쌍이 아니라 ret은 그대로.)
 * 7 -> mul = 2 / 2, ret = 22
 * 8 -> mul = 1 * 2, ret = 22
 * 9 -> mul = 2 * 3, ret = 22
 * 10 -> mul = 6 / 3, ret = 22 + 6
 * 11 -> mul = 2 / 2, ret = 28
 * 종료
 *
 *
 */
public class Q2504 {

    private static int solution(String input) {
        Stack<Character> stack = new Stack<>();
        int mul = 1;
        int ret = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if ((c == ']' || c == ')') && stack.isEmpty()) {
                return 0;
            } else if (c == '(') {
                mul *= 2;
                stack.push(c);
            } else if (c == '[') {
                mul *= 3;
                stack.push(c);
            } else if (c == ')') {
                if (stack.peek() == '(' ) {
                    stack.pop();
                    if (input.charAt(i - 1) == '(')
                        ret += mul;
                    mul /= 2;
                }
            } else if (c == ']') {
                if (stack.peek() == '[') {
                    stack.pop();
                    if (input.charAt(i - 1) == '[')
                        ret += mul;
                    mul /= 3;
                }
            }
        }

        return stack.size() == 0 ? ret : 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        System.out.println(solution(input));
    }

}
