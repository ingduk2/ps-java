package ps.baekjoon.solvedac.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 패션왕 신해빈
 * https://www.acmicpc.net/problem/9375
 *
 * 같은 이름의 의상은 없음.
 * 경우의수는 각 의상 수 + 1 의 곱
 * 모자 2, 안경 3, 옷 4 = 3 * 4 * 5
 *
 * 경우의 수에서 아무것도 안입는 경우 -1
 *
 */
public class Q9375 {

    public static void main(String[] args) {
        Input in = new Input();
        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                in.next();
                String kind = in.next();
                map.put(kind, map.getOrDefault(kind, 1) + 1);
            }

            AtomicInteger sum = new AtomicInteger(1);
            map.forEach((k, v) -> sum.updateAndGet(v1 -> v1 * v));
            System.out.println(sum.decrementAndGet());
        }
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
