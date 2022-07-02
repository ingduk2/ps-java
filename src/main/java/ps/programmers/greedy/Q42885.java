package ps.programmers.greedy;

import java.util.Arrays;

/**
 * 구명보트 (양쪽에서 비교)
 * 오름차순 정렬.
 * 시작, 끝에서 2명이 탈수있는지 확인.
 * 2명이 탈수있으면 시작++ 끝--
 * 탈수없으면 끝-- (무거운사람 혼자 태우기)
 * https://programmers.co.kr/learn/courses/30/lessons/42885
 */
public class Q42885 {

    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int right = people.length - 1;
        int left = 0;

        int cnt = 0;
        while (true) {
            if (left > right) break;
            if (people[left] + people[right] <= limit) left ++;
            right --;
            cnt ++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        int[] people = {70, 50, 80, 50};
        int limit = 100;
        System.out.println(new Q42885().solution(people, limit));

        people = new int[]{70, 80, 50};
        limit = 100;
        System.out.println(new Q42885().solution(people, limit));
    }
}
