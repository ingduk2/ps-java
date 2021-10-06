package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 돌 게임 2
 * https://www.acmicpc.net/problem/9656
 *
 * 1 CY
 * sk 1
 *
 * 2 SK
 * sk 1
 * cy 1
 *
 * 3 CY
 * sk 1
 * cy 1
 * sk 1
 *
 * 4 SK
 * sk 3
 * cy 1
 *
 * sk 1
 * cy 3
 *
 * sk 1
 * cy 1
 * sk 1
 * cy 1
 *
 * 5 CY
 * sk 1
 * cy 1
 * sk 1
 * cy 1
 * sk 1
 *
 * sk 1
 * cy 3
 * sk 1
 *
 * 돌 가져가는 개수가 홀수이므로
 * 짝수일떄는 cy가 마지막 가져감 -> sk 승리
 * 홀수일떄는 sk가 마지막 가져감 -> cy 승
 */
public class Q9656 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        if (n % 2 == 0) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
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

        public long nextLong() { return Long.parseLong(next()); }

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
