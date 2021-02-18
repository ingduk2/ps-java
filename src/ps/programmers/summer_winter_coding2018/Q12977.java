package ps.programmers.summer_winter_coding2018;

import java.util.ArrayList;

/**
 * 소수 만들기
 * https://programmers.co.kr/learn/courses/30/lessons/12977
 */
public class Q12977 {

    private static class Combination {
        private int n;
        private int r;
        private int[] now; // 현재 조합
        private ArrayList<ArrayList<Integer>> result; // 모든 조합

        public ArrayList<ArrayList<Integer>> getResult() {
            return result;
        }

        public Combination(int n, int r) {
            this.n = n;
            this.r = r;
            this.now = new int[r];
            this.result = new ArrayList<>();
        }

        public void combination(int[] arr, int depth, int index, int target) {
            if (depth == r) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int i = 0; i < now.length; i++) {
                    temp.add(arr[now[i]]);
                }
                result.add(temp);
                return;
            }
            if (target == n) return;
            now[index] = target;
            combination(arr, depth + 1, index + 1, target + 1);
            combination(arr, depth, index, target + 1); }
    }

    private static boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }
        for (int i = 2; i*i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }


    public int solution(int[] nums) {
        int answer = 0;

        Combination comb = new Combination(nums.length, 3);
        comb.combination(nums, 0, 0, 0);
        ArrayList<ArrayList<Integer>> result = comb.getResult();

        for (ArrayList<Integer> integers : result) {
            int sum = integers.stream().mapToInt(i -> i).sum();
            if (isPrime(sum)) answer += 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Q12977().solution(new int[]{1, 2, 3, 4}));
        System.out.println(new Q12977().solution(new int[]{1, 2, 7, 6, 4}));
    }
}
