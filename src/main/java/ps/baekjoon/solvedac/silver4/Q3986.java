package ps.baekjoon.solvedac.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 좋은 단어
 *
 * ABAB X
 * AA BB O
 * A BB A O
 *
 * stack 에 넣고 같은 단어 나오면 pop
 * stack size 가 0 이면 좋은 단어
 *
 */
public class Q3986 {

    private static int cnt;

    private static void solve(String str) {
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (stack.peek() == c) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        if (stack.isEmpty()) cnt++;
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String str = in.next();
            solve(str);
        }

        System.out.println(cnt);
    }


    private static class Input {
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
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
