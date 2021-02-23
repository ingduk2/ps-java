package ps.programmers.kakao.blind2018;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * [1차] 다트 게임
 * https://programmers.co.kr/learn/courses/30/lessons/17682
 */
public class Q17682 {

    Stack<Integer> stack = new Stack<>();

    private void makeScore(char c) {

        Integer pop = stack.pop();

        if (c == 'S') {
            stack.push((int) Math.pow(pop, 1));
            return;
        }
        if (c == 'D') {
            stack.push((int) Math.pow(pop, 2));
            return;
        }
        if (c == 'T') {
            stack.push((int) Math.pow(pop, 3));
            return;
        }
        if (c == '*') {
            if (stack.size() == 0) {
                stack.push(pop * 2);
            } else if (stack.size() > 0) {
                Integer pop2 = stack.pop();
                stack.push(pop2 * 2);
                stack.push(pop * 2);
            }
            return;
        }
        if (c == '#') {
            stack.push(pop * -1);
            return;
        }
    }

    private List<String> makeDartCmd(String dartResult) {
        List<String> result = new ArrayList<>();

        String numStr = "";
        for (int i = 0; i < dartResult.length(); i++) {
            char c = dartResult.charAt(i);
            if (!Character.isDigit(c)) {
                if (!numStr.equals("")) {
                    result.add(numStr);
                }
                result.add(String.valueOf(c));
                numStr = "";
            } else {
                numStr += c;
            }
        }

        return result;
    }

    public int solution(String dartResult) {
        List<String> darts = makeDartCmd(dartResult);

        for (String dart : darts) {
            if (dart.chars().allMatch(Character::isDigit)) {
                stack.push(Integer.parseInt(dart));
            } else {
                makeScore(dart.charAt(0));
            }
        }

        return stack.stream().mapToInt(i -> i).sum();
    }

    public static void main(String[] args) {

        System.out.println(new Q17682().solution("1S2D*3T") );
        System.out.println(new Q17682().solution("1D2S#10S") );
        System.out.println(new Q17682().solution("1D2S0T") );
        System.out.println(new Q17682().solution("1S*2T*3S") );
        System.out.println(new Q17682().solution("1D#2S*3S") );
        System.out.println(new Q17682().solution("1T2D3D#") );
        System.out.println(new Q17682().solution("1D2S3T*") );
    }
}
