package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 막대기
 * https://www.acmicpc.net/problem/1094
 *
 * 23
 * 64 > 23
 * 1. 32 32
 * 2. 32 > 23 , 32
 *
 * 32 > 23
 * 1. 16 16
 * 2. 16 < 23, 16 16
 *
 * 16 + 16 > 23
 * 1. 8 8 16
 * 2. 24 > 23, 8 16
 *
 * 8 + 16 > 23
 * 1. 4 4 16
 * 2. 20 < 23, 4 4 16
 *
 * 4 + 4 + 16 > 23
 * 1. 2 2 4 16
 * 2. 22 < 23, 2 2 4 16
 *
 * 2 + 2 + 4 + 16 > 23
 * 1. 1 1 2 4 16
 * 2. 25 > 23, 1 2 4 16
 *
 * 1 + 2 + 4 + 16 = 23
 *
 *
 */
public class Q1094 {

    private static int getSticksSum(LinkedList<Integer> sticks) {
        int sum = 0;
        for (Integer stick : sticks) {
            sum += stick;
        }
        return sum;
    }

    public static void main(String[] args) {
        Input in = new Input();
        int x = in.nextInt();

        int stick = 64;
        LinkedList<Integer> sticks = new LinkedList<>();
        sticks.add(stick);

        int sum = getSticksSum(sticks);
        while (x != getSticksSum(sticks)) {

            if (sum > x) {
                //1.
                int min = sticks.size() == 1 ? sticks.pollFirst() : sticks.pollLast();
                min /= 2;

                //2.
                if (min + getSticksSum(sticks) >= x) {
                    sticks.addLast(min);
                } else {
                    sticks.addLast(min);
                    sticks.addLast(min);
                }
            }
        }

        System.out.println(sticks.size());
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
