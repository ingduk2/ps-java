package ps.baekjoon.solvedac.bronze2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시험 감독
 * https://www.acmicpc.net/problem/13458
 *
 * 총감독관 한 시험장 B
 * 부감독관 한 시험장 C
 * 시험장 총감독관은 1명, 부감독관 여러명
 * 모든 응시생 감시 감도고간 수 최소값.
 *
 * 1
 * 1
 * 1 1
 * 1명
 *
 *
 * 3
 * 3 4 5
 * 2 2
 * 3(2 + 1/2 + 1)
 * 4(2 + 2/2 + 1)
 * 5(2 + 3/2 + 1)
 * 7
 *
 * 총감독관 1명 + 부감독관으로 Ai 의 수를 넘게 할당.
 * 부감독관 수
 * if (Ai - 총감독관가능수 % 부감독관 == 0)
 *    (Ai - 총감독관가능수) / 부감독관
 * else
 *    (Ai - 총감독관가능수) / 부감독관 + 1
 *
 *
 * 모든 값이 1000000 일 경우 int 범위 초과할 수 있음
 * 1000000개 교실
 * 1000000명씩
 * 감독관은 1명씩이라면
 * 1000000 * 1000000 대략 cnt 가 너무커짐.
 *
 */
public class Q13458 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int b = in.nextInt();
        int c = in.nextInt();

        long cnt = 0;
        for (int i : a) {
            cnt += 1;
            if (i > b){
                if ((i - b) % c == 0) {
                    cnt += (i - b) / c;
                } else {
                    cnt += (i - b) / c + 1;
                }
            }
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
