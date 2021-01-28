package ps.thiscodingtest.previous_problems.sort;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 국영수
 */
/*
12
Junkyu 50 60 100
Sangkeun 80 60 50
Sunyoung 80 70 100
Soong 50 60 90
Haebin 50 60 100
Kangsoo 60 80 100
Donghyuk 80 60 100
Sei 70 70 70
Wonseob 70 70 90
Sanghyun 70 70 80
nsj 80 80 80
Taewhan 50 60 90
 */
public class Q23 {

    private static class Score {
        private String name;
        private int korean;
        private int english;
        private int math;

        public Score(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        public String getName() {
            return name;
        }

        public int getKorean() {
            return korean;
        }

        public int getEnglish() {
            return english;
        }

        public int getMath() {
            return math;
        }

        @Override
        public String toString() {
            return "Score{" +
                    "name='" + name + '\'' +
                    ", korean=" + korean +
                    ", english=" + english +
                    ", math=" + math +
                    '}';
        }


    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        List<Score> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split(" ");
            list.add(new Score(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3])));
        }

        list = list.stream().sorted(
                Comparator.comparingInt(Score::getKorean).reversed()
                .thenComparingInt(Score::getEnglish)
                .thenComparing(Comparator.comparingInt(Score::getMath).reversed())
                .thenComparing(Score::getName)
               ).collect(Collectors.toList());

//        Collections.sort(list, new Comparator<Score>() {
//            @Override
//            public int compare(Score o1, Score o2) {
//                if (o1.korean == o2.korean && o1.english == o2.english && o1.math == o2.math) {
//                    return o1.name.compareTo(o2.name);
//                }
//                if (o1.korean == o2.korean && o1.english == o2.english) {
//                    return Integer.compare(o2.math, o1.math);
//                }
//                if (o1.korean == o2.korean) {
//                    return Integer.compare(o1.english, o2.english);
//                }
//                return Integer.compare(o2.korean, o1.korean);
//            }
//        });

        for (Score score : list) {
            System.out.println(score.getName());
        }

    }
}
