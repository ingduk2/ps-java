package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 제로
 * https://www.acmicpc.net/problem/10773
 *
 * 0이 나오면 기존에 저장한 숫자들을 지워줘야해서
 * 스택 사용.
 */
public class Q10773 {

    public static void main(String[] args) {
        Input in = new Input();
        int k = in.nextInt();

        Stack<Integer> numbers = new Stack<>();
        for (int i = 0; i < k; i++) {
            int num = in.nextInt();
            if (num != 0) {
                numbers.push(num);
            } else {
                numbers.pop();
            }
        }

        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        System.out.println(sum);
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
