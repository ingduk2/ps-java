package ps.baekjoon.solvedac.silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 에디터
 * https://www.acmicpc.net/problem/1406
 *
 * 2개의 스택 사용.
 * 1번 스택 끝이 커서 역할.
 *
 * L 왼쪽으로 옮기므로 1번 pop 2번 push
 * stack1 pop -> stack2 push
 *
 * D 오른쪾으로 옮겨야 하므로 2 번 pop 1번 push
 * stack2 pop -> stack1 push
 *
 * B 문자 삭제이므로 1번 pop
 * stack1 pop
 *
 * P $ 문자 추가.
 * stack1 push
 *
 * 마지막 stack1 pop -> stack2 push
 * stack2 pop
 *
 * abcd
 * 3
 * P x
 * L
 * P y
 *
 * [abcd], []
 * [abcdx], []
 * [abcd], [x]
 * [abcdy], [x]
 * [], [abcdyx]
 * pop
 */
public class Q1406_Stack {

    public static void main(String[] args) {
        Input in = new Input();
        String str = in.next();
        int n = str.length();
        int m = in.nextInt();

        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();

        for (int i = 0; i < n; i++) {
            stack1.push(str.charAt(i));
        }

        for (int i = 0; i < m; i++) {
            String cmd = in.next();

            switch (cmd) {
                case "P":
                    stack1.push(in.next().charAt(0));
                    break;
                case "L":
                    if (!stack1.empty()) stack2.push(stack1.pop());
                    break;
                case "D":
                    if (!stack2.empty()) stack1.push(stack2.pop());
                    break;
                case "B":
                    if (!stack1.empty()) stack1.pop();
                    break;
            }
        }

        while (!stack1.empty()) stack2.push(stack1.pop());

        StringBuilder result = new StringBuilder();
        while (!stack2.empty()) {
            result.append(stack2.pop());
        }

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
