package ps.baekjoon.solvedac.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 나이순 정렬
 * https://www.acmicpc.net/problem/10814
 *
 * 정렬
 */
public class Q10814 {

    private static class Member{
        private int order;
        private int age;
        private String name;

        public Member(int order, int age, String name) {
            this.order = order;
            this.age = age;
            this.name = name;
        }

    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        List<Member> members = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            members.add(new Member(i, in.nextInt(), in.next()));
        }

        members.sort((o1, o2) -> {
            if (o1.age == o2.age) {
                return Integer.compare(o1.order, o2.order);
            } else {
                return Integer.compare(o1.age, o2.age);
            }
        });

        for (Member member : members) {
            System.out.println(member.age + " " + member.name);
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
