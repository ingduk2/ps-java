package ps.baekjoon.solvedac.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 베스트셀러
 * https://www.acmicpc.net/problem/1302
 *
 * 1. map 으로 title cnt 누적
 * 2. max cnt 찾기
 * 3. key -> list 로 변환 후 사전순 정렬
 * 4. 사전순 keys 순회하면서 map.get cnt == max 찾기
 */
public class Q1302 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String title = in.next();
            if (map.containsKey(title)) {
                map.put(title, map.get(title) + 1);
            } else {
                map.put(title, 1);
            }
        }

        int max = 0;
        for (int value : map.values()) {
            max = Math.max(max, value);
        }

        String result = "";
        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);

        for (String key : keys) {
            if (map.get(key) == max) {
                result = key;
                break;
            }
        }

        System.out.println(result);
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

        public String line() throws IOException {
            return br.readLine();
        }
    }
}
