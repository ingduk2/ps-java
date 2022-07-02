package ps.baekjoon.solvedac.bronze2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 거스름돈
 * https://www.acmicpc.net/problem/5585
 *
 * 거스름돈 개수 가장 적게 잔돈
 * 380
 * 1000 - 380 = 620
 * 500 * 1 120
 * 100 * 1 20
 * 10 * 2 0
 * 4
 *
 * 1
 * 1000 - 1 = 999
 * 500 * 1 500
 * 100 * 4 900
 * 50 * 1 950
 * 10 * 4 990
 * 5 * 1 995
 * 1 * 4 999
 * 15
 *
 * 큰돈부터 가능한 개수만큼 빼면서 0 될때까지 카운트
 */
public class Q5585 {

    private static int money = 1000;
    private static int[] changes = {500, 100, 50, 10, 5, 1};

    public static void main(String[] args) {
        Input in = new Input();
        int price = in.nextInt();
        int n = money - price;

        int cnt = 0;
        for (int c : changes) {
            int changeCnt = n / c;
            cnt += changeCnt;
            n -= c * changeCnt;

            if (n == 0) break;
        }

        System.out.println(cnt);
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
