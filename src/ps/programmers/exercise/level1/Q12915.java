package ps.programmers.exercise.level1;

import java.util.Arrays;

/**
 * 문자열 내 마음대로 정렬하기
 * https://programmers.co.kr/learn/courses/30/lessons/12915
 */
public class Q12915 {

    public String[] solution(String[] strings, int n) {

        Arrays.sort(strings, (o1, o2) -> {
            if (o1.charAt(n) != o2.charAt(n)) {
                return Character.compare(o1.charAt(n), o2.charAt(n));
            }
            return o1.compareTo(o2);
        });

        return strings;
    }

    public static void main(String[] arg) {
        String[] str = {"sun", "bed", "car"};
        int n = 1;
//        [car, bed, sun]
        System.out.println(Arrays.toString(new Q12915().solution(str, n)));

        str = new String[]{"abce", "abcd", "cdx"};
        n = 2;
//        [abcd, abce, cdx]
        System.out.println(Arrays.toString(new Q12915().solution(str, n)));
    }
}
