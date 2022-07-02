package ps.baekjoon.solvedac.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Z
 * https://www.acmicpc.net/problem/1074
 *
 * 분할정복
 * 4개기준
 *
 * 4등분은 2의 제곱승이므로 /2로 쪼갠다.
 * size가 2 일때만 Z로 숫자 입력.
 * 반복
 *
 * 2^15
 * 32768
 * 배열 메모리초과..
 * 1073741824 배열크기가 너무커짐
 * 배열을 사용하면 안될듯..?
 * 그냥 단순히 돌려도 시간초과날듯함.
 *
 * 좌표를 가지고 포함되는 영역만 검사하는식으로 풀어야하는듯
 * 좌표가 영역에 포함되면 재귀, 포함되지 않으면 그 영역 수(size^2) 만큼 +
 * */
public class Q1074 {

    private static int num = 0;
    private static int r = 0;
    private static int c = 0;

    private static void z(int x, int y, int size) {
        if (x == r && y == c) {
            System.out.println(num);
            return;
        }

        if (r < x + size && r >= x && c < y + size && c >= y) {
            int s = size / 2;
            z(x, y, s);
            z(x, y + s, s);
            z(x + s, y, s);
            z(x + s, y + s, s);
        } else {
            num += size * size;
        }

    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        r = in.nextInt();
        c = in.nextInt();

        int size = (int) Math.pow(2, n);

        z(0, 0, size);
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
