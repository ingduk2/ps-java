package ps.programmers.exercise;

import java.util.HashSet;
import java.util.Set;

/**
 * 폰켓몬
 * https://programmers.co.kr/learn/courses/30/lessons/1845
 */
public class Q1845 {

    public int solution(int[] nums) {

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int max = nums.length / 2;
        int setLen = set.size();

//        return max <= setLen ? max : setLen;
        return Math.min(max, setLen);
    }

    public static void main(String[] args) {
        int[] nums = {3,1,2,3};
        System.out.println(new Q1845().solution(nums));
        nums = new int[]{3, 3, 3, 2, 2, 4};
        System.out.println(new Q1845().solution(nums));
        nums = new int[]{3, 3, 3, 2, 2, 2};
        System.out.println(new Q1845().solution(nums));
    }
}
