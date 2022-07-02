package ps.baekjoon.solvedac.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 카드
 * https://www.acmicpc.net/problem/11652
 *
 * hashmap key 정렬이 양수면 같은 값일 경우 작은수가 앞에있는데 음수일 경우 정렬 따로 필요
 * 4
 * -1
 * -1
 * -2
 * -2
 * -> -1
 *
 * 4
 * 1
 * 1
 * 2
 * 2
 * -> 1
 *
 * -2가 나와야하는데 -1이 나옴.
 *
 * if (countMap.get(o1).equals(countMap.get(o2))) {
 * Integer 로 나오기때문에 == 하면 틀림.. 128 넘어가면 틀려짐
 *
 * https://meetup.toast.com/posts/185
 */
public class Q11652 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        Map<Long, Integer> countMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long num = in.nextLong();
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        List<Long> keyList = new ArrayList<>(countMap.keySet());
        keyList.sort(((o1, o2) -> {
            if (countMap.get(o1).equals(countMap.get(o2))) {
                return o1.compareTo(o2);
            }
            return countMap.get(o2) - countMap.get(o1);
        }));

        System.out.println(keyList.get(0));
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
