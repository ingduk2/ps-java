package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 영화감독 숌
 * https://www.acmicpc.net/problem/1436
 * <p>
 * <p>
 * 6연속.
 * 666
 * 1666
 * 2666
 * 3666
 * 4666
 * 5666
 * 6660
 * 6661
 * 6662
 * 6663
 * 6664
 * 6665
 * 6666
 *
 * - String 변환 후 contains 666
 * - 666찾는것 구현.
 */
public class Q1436 {

    private static boolean hasdestroy(int num) {
        String s = Integer.toString(num);
        int cnt = 0;
        int point = -1; // 0이면 다음 6을 처리해버려서 10번쨰에 6366 이 나옴.
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '6' && (point == -1 || point == i - 1)) {
                cnt++;
                point = i;
            } else if (c != '6') { //중간에 다른숫자 나오면 초기화.
                point = -1;
                cnt = 0;
            }

            //6 3개이상 찾으면 true
            if (cnt >= 3) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        int cnt = 1;
        int num = 666;

        while (cnt != n) {
            num++;

//            if (Integer.toString(num).contains("666")) {
            if (hasdestroy(num)) {
                cnt++;
            }
        }

        System.out.println(num);
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
