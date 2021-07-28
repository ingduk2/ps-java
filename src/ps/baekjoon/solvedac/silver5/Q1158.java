package ps.baekjoon.solvedac.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 요세푸스 문제
 * https://www.acmicpc.net/problem/1158
 *
 * 원이므로 큐에서 빼고 다시 넣기.
 * k 번 하면 delete.
 *
 * 7, 3 (3, 6, 2, 7, 5, 1, 4)
 * 1 2 3 4 5 6 7
 * 2 3 4 5 6 7 1
 * 3 4 5 6 7 1 2 - delete 3
 * 4 5 6 7 1 2
 * 5 6 7 1 2 4
 * 6 7 1 2 4 5 - delete 6
 * 7 1 2 4 5
 * 1 2 4 5 7
 * 2 4 5 7 1 - delete 2
 * 4 5 7 1
 * 5 7 1 4
 * 7 1 4 5 - delete 7
 * 1 4 5
 * 4 5 1
 * 5 1 4 - delete 5
 * 1 4
 * 4 1
 * 1 4 - delete 1
 * 4 - delete 4
 *
 * - 시간초과..
 * - q.size > 1 로 해서 1개 남으면 처리했는데 이게 문제였던듯..?
 * - cnt 가 1로 줘서 k 가 1인 경우에 poll 하고 cnt가 2로 넘어가서 꼬임..
 */
public class Q1158 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int k = in.nextInt();

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            q.offer(i);
        }

        int cnt = 1;
        StringBuilder result = new StringBuilder();
        result.append("<");
        while (!q.isEmpty()) {
            if (cnt == k) {
                result.append(q.poll()).append(", ");
                cnt = 0;
            } else {
                q.offer(q.poll());
            }

            cnt++;
        }

        result.setLength(result.length() - 2);
        result.append(">");
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
