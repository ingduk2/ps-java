package ps.baekjoon.solvedac.silver1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 신입 사원
 * https://www.acmicpc.net/problem/1946
 *
 * 다른 모든 사원과 비교시 둘다 등수가 크면 탈락.
 * 둘 중 하나라도 작으면 ok
 *
 * 1. 서류순위 오름차순 정렬
 * 2. 1위는 채용, 1위의 면접 순위 저장
 * 3. 1위의 면접 순위보다 크면 미채용, 작으면 채용 후 면접순위 면접순위 저장
 * 반복
 *
 * 서류순위 정렬이므로, 면접순위가 중요
 * 1등보다 면접순위가 작은 경우가 나오면
 * 다음부터 이미 서류순위가 낮기 때문에 새로운 면접순위보다 낮아야만 채용 가능.
 *
 *
 */
public class Q1946 {

    private static class Employee {
        private int document;
        private int interview;

        public Employee(int document, int interview) {
            this.document = document;
            this.interview = interview;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "document=" + document +
                    ", interview=" + interview +
                    '}';
        }
    }

    private static int solve(List<Employee> employees) {
        int cnt = 1;
        employees.sort(Comparator.comparingInt(o -> o.document));

        int rank = employees.get(0).interview;
        int size = employees.size();
        for (int i = 1; i < size; i++) {
            int interview = employees.get(i).interview;
            if (interview < rank) {
                cnt++;
                rank = interview;
            }
        }

        return cnt;
    }


    public static void main(String[] args) {
        Input in = new Input();
        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            List<Employee> employees = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                employees.add(new Employee(in.nextInt(), in.nextInt()));
            }
            System.out.println(solve(employees));
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
