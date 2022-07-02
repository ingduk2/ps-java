package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 중복 빼고 정렬하기
 * https://www.acmicpc.net/problem/10867
 *
 * 중복을 입력받으면서 빼야헐지
 * map 으로 중복제거.
 */
public class Q10867 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            if (!map.containsKey(num)) {
                map.put(num, 1);
                list.add(num);
            }
        }

        Collections.sort(list);
        for (Integer integer : list) {
            System.out.print(integer + " ");
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

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
