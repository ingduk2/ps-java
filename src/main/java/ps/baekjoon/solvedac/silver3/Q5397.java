package ps.baekjoon.solvedac.silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 키로거
 *
 * - 앞에 글자 지움
 * < > 화살표
 *
 * stack1 커서 stack2
 * - stack1 pop
 * < stack2.push(stack1.pop)
 * > stack1.push(stack2.pop)
 *
 */
public class Q5397 {

    public static void main(String[] args) {
        Input in = new Input();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            String l = in.next();
            Stack<Character> stack1 = new Stack<>();
            Stack<Character> stack2 = new Stack<>();

            for (int j = 0; j < l.length(); j++) {
                char c = l.charAt(j);
                if (c == '<') {
                    if (!stack1.isEmpty()) stack2.push(stack1.pop());
                } else if (c == '>') {
                    if (!stack2.isEmpty()) stack1.push(stack2.pop());
                } else if (c == '-') {
                    if (!stack1.isEmpty()) stack1.pop();
                } else {
                    stack1.push(c);
                }
            }

            while(!stack1.isEmpty()) stack2.push(stack1.pop());

            StringBuilder sb = new StringBuilder();
            while(!stack2.isEmpty()) sb.append(stack2.pop());

            System.out.println(sb);
        }
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
