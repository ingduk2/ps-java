package ps.baekjoon.solvedac.bronze2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 나누기
 * https://www.acmicpc.net/problem/1075
 *
 * 1. 뒤 2자리를 00으로 변환. n/100 * 100
 * 2. 변환한 수 < (변환한 수 + 100) 까지 f로 나누어 떨어지는지 확인
 * 3. 떨어지면 뒤 2자리수 저장. i % 100 하면 2자리수만 남음
 * 4. 한 자리이면 앞에 0을 추가해서 두 자리로 만들어야 함.
 *
 */
public class Q1075 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int f = in.nextInt();
        int result = 0;

        int start = n / 100 * 100;
        int end = start + 100;
        for (int i = start; i < end; i++) {
            if (i % f == 0) {
                result = i % 100;
                break;
            }
        }

        if (result < 10) System.out.print("0");
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
