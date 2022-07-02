package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 요세푸스 문제0
 * https://www.acmicpc.net/problem/11866
 *
 * 1 2 3 4 5 6 7
 * 3
 * 1 2 3 4 5 6 7
 * 6
 * 제거하면 순서가 안맞는것 같아서 제거안하고
 * index 반복.
 * for문 돌면서 index k개 체크할려고 했는데
 *
 * 큐로 뒤로 돌려넣으면서 하는방법이 더 좋을듯.
 * k번 돌면 pop, 아니면 돌려넣기
 * 1 2 3 4 5 6 7
 * 2 3 4 5 6 7 1
 * 3 4 5 6 7 1 2 , 3
 *
 * 4 5 6 7 1 2
 * 5 6 7 1 2 4
 * 6 7 1 2 4 5 , 6
 *
 * 7 1 2 4 5
 * 1 2 4 5 7
 * 2 4 5 7 1 , 2
 *
 *
 * 그렇게 n개 나올떄까지.
 */
public class Q11866 {

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();
        int k = in.nextInt();

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            q.offer(i);
        }

        List<Integer> results = new ArrayList<>();
        int idx = 1;
        while (results.size() < n) {
            Integer num = q.poll();
            if (idx < k) {
                q.offer(num);
            } else if (idx == k) {
                results.add(num);
                idx = 0;
            }
            idx++;
        }

        System.out.print("<");
        System.out.print(results.stream().map(Object::toString).collect(Collectors.joining(", ")));
        System.out.print(">");
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
