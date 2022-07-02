package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 스택 수열
 * https://www.acmicpc.net/problem/1874
 *
 * arr[i] >= num 면 stack.push +
 * arr[i] == stack.peek 면 pop -
 * arr[i] < stack.peek 면 NO
 *
 * 스택의 peek > arr[i]현재 값보다 크면
 * 불가능하다.
 */
public class Q1874 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        StringBuilder result = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int idx = 0;

        stack.push(1);
        result.append("+\n");

        int num = 2;
        while (idx < n) {
            if (arr[idx] >= num) {
                result.append("+\n");
                stack.push(num);
                num++;
            } else if (arr[idx] == stack.peek()) {
                idx++;
                result.append("-\n");
                stack.pop();
            } else if (arr[idx] < stack.peek()) {
                result.setLength(0);
                result.append("NO");
                break;
            }
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
    }
}
