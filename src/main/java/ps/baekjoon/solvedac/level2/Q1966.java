package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 프린터 큐
 * https://www.acmicpc.net/problem/1966
 *
 * 간단하게 생각하고 큐와 우선순위라서 우선순위큐 사용. Pair로 우선순위큐쓰니까 이상하네..
 * 뭔가 잘못한듯 정렬을 해버리면서 index(number)가 안맞음
 *
 * 우선순위만 priorityQueue 로 reverse.
 * Queue에는 입력대로 담음
 * A B C D
 * 2 1 4 3
 *
 * 4 3 2 1
 *
 * B C D A
 * 1 4 3 2
 *
 * C D A B
 * 4 3 2 1
 * 우선순위가 같으므로 pop
 *
 * q의 priority == pq priority 면 pop -> m == q.num break;
 * != 면 q.pop -> q.offer
 *
 * 큐가 비면 맨 마지막까지 카운트..
 *
 */
public class Q1966 {
    private static class Document {
        private int num;
        private int priority;

        public Document(int num, int priority) {
            this.num = num;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "Document{" +
                    "num=" + num +
                    ", priority=" + priority +
                    '}';
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int t = in.nextInt();

        Queue<Document> q = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < t; i++) {
            int count = 0;
            int n = in.nextInt();
            int m = in.nextInt();
            for (int j = 0; j < n; j++) {
                int pri = in.nextInt();
                q.offer(new Document(j, pri));
                pq.offer(pri);
            }

            while (!q.isEmpty()) {
                if (q.peek().priority == pq.peek()) {
                    Document poll = q.poll();
                    pq.poll();
                    count++;
                    if (poll.num == m) {
                        break;
                    }
                } else {
                    q.offer(q.poll());
                }

            }
            System.out.println(count);
            pq.clear();
            q.clear();
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
