package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 카드2
 * https://www.acmicpc.net/problem/2164
 *
 * 0번째 카드 제거.
 * 그다음 0번째 카드를 맨 뒤로.
 */
public class Q2164 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        Queue<Integer> cards = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            cards.add(i);
        }

        while (cards.size() > 1) {
            cards.poll();
            Integer next = cards.poll();
            cards.add(next);
        }

        System.out.println(cards.poll());
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
