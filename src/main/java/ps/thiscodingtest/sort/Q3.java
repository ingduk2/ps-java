package ps.thiscodingtest.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 성적이 낮은 순서로 학생 출력하기
 */
public class Q3 {

    public static class Student implements Comparable<Student>{
        private String name;
        private int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(Student o) {
            if (this.score < o.score) {
                return -1;
            }
            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Student> list = new ArrayList<>();
        while (n-- > 0) {
            String[] line = br.readLine().split(" ");
            list.add(new Student(line[0], Integer.parseInt(line[1])));
        }

//        Collections.sort(list);
//        for (Student student : list) {
//            System.out.print(student.getName() + " ");
//        }

        list.stream().sorted().forEach(student -> System.out.print(student.getName() + " "));
    }
}
