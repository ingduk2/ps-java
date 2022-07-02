package ps.baekjoon.solvedac.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 회전하는 큐
 * https://www.acmicpc.net/problem/1021
 *
 * 첫 번째 원소를 뽑아낸다. q.poll;
 * 왼쪽으로 한 칸 이동시킨다. q.add(q.poll)
 * 오른쪽으로 한 칸 이동시킨다. q.addfirst(q.pollList)
 *
 * LinkedList 사용
 * 1. target == peek 면 poll;
 * 2. 다르면 왼쪽에서 가까운지 오른쪽에거 가까운지 체크(중간 idx와 targetIdx 로 구분도 가능)
 * 3. 그 후에 가까운쪽으로 회전 (오른쪽 한칸은 + 1번 회전해야 맞음)
 *
 * 다시 1번부터
 *
 */
public class Q1021 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int m = in.nextInt();

        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            q.add(i);
        }

        Queue<Integer> numbers = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            numbers.add(in.nextInt());
        }

        int cnt = 0;
        while (!numbers.isEmpty()) {
            int target = numbers.poll();
            int r = rightDist(q, target);
            int l = leftDist(q, target);

            if (!q.isEmpty() && target != q.peek()) {
                if (l > r) {
                    for (int i = 0; i < r + 1; i++) {
                        q.addFirst(q.pollLast());
                        cnt++;
                    }
                } else {
                    for (int i = 0; i < l; i++) {
                        q.add(q.poll());
                        cnt++;
                    }
                }
            }
            q.poll();
        }

        System.out.println(cnt);
    }

    private static int leftDist(LinkedList<Integer> q, int target) {
        int distance = 0;
        for (Integer i: q) {
            if (target == i) break;
            distance++;
        }

        return distance;
    }

    private static int rightDist(LinkedList<Integer> q, int target) {
        int distance = 0;
        for (int i = q.size() - 1; i >= 0; i--) {
            Integer num = q.get(i);
            if (num == target) break;
            distance++;
        }

        return distance;
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
