package ps.baekjoon.solvedac.silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 단어 뒤집기 2
 * https://www.acmicpc.net/problem/17413
 *
 * 뒤집기는 stack 사용
 *
 * '<' 태그 닫힐때까지 add(stack이 차있으면 pop으로 add)
 * ' ' (stack이 차있으면 pop으로 add)
 * 나머지는 stack push
 *
 * 마지막에 stack이 차있으면 pop으로 add
 */
public class Q17413 {

    public static void main(String[] args) throws IOException {
        Input in = new Input();
        char[] chars = in.line().toCharArray();

        StringBuilder result = new StringBuilder();

        Stack<Character> st = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '<') {
                while(!st.isEmpty()) result.append(st.pop());
                for (int j = i; j < chars.length; j++) {
                    result.append(chars[j]);
                    if (chars[j] == '>') {
                        i = j;
                        break;
                    }
                }
            } else if (c == ' ') {
                while(!st.isEmpty()) result.append(st.pop());
                result.append(' ');
            } else {
                st.push(c);
            }
        }

        while(!st.isEmpty()) result.append(st.pop());
        System.out.println(result);

    }

    private static class Input {
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        private String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
