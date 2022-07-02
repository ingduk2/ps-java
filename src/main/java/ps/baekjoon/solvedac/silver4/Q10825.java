package ps.baekjoon.solvedac.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 국영수
 * https://www.acmicpc.net/problem/10825
 */
public class Q10825 {

    private static class Student{
        private String name;
        private int korean;
        private int english;
        private int math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", korean=" + korean +
                    ", english=" + english +
                    ", math=" + math +
                    '}';
        }
    }

    public static void main(String[] args) {
        Input in = new Input();
        int n = in.nextInt();

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            students.add(new Student(in.next(), in.nextInt(), in.nextInt(), in.nextInt()));
        }

        students.sort((o1, o2) -> {
            if (o1.korean == o2.korean && o1.english == o2.english && o1.math == o2.math) {
                return o1.name.compareTo(o2.name);
            }
            if (o1.korean == o2.korean && o1.english == o2.english) {
                return Integer.compare(o2.math, o1.math);
            }
            if (o2.korean == o1.korean) {
                return Integer.compare(o1.english, o2.english);
            }

            return Integer.compare(o2.korean, o1.korean);
        });

        for (Student student : students) {
            System.out.println(student.name);
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
