package ps.thiscodingtest.previous_problems.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 무지의 먹방 라이브
 * [8, 6, 4], 15
 * {4, 3}, {6, 2}, {8, 1}
 * cycle 3 * 4 = 12
 * 15 - 12 = 3
 * {6, 2}, {8, 1} 음식 다먹을 사이클이 안됨.
 * 음식 번호 기준으로 정렬. [1, 2]
 * (k - time) % len -> 3%2 -> 1 위치의 2번 음식.
 */
public class Q6_Answer {

    private static class Food{
        private int time;
        private int number;

        public Food(int time, int number) {
            this.time = time;
            this.number = number;
        }

        public int getTime() {
            return time;
        }

        public int getNumber() {
            return number;
        }

        @Override
        public String toString() {
            return "Food{" +
                    "time=" + time +
                    ", number=" + number +
                    '}';
        }
    }


    public int solution(int[] food_times, long k) {

        long sum = 0;
        for (int food_time : food_times) {
            sum += food_time;
        }
        if (sum <= k) return -1;

        PriorityQueue<Food> pq = new PriorityQueue<>(Comparator.comparing(Food::getTime));

        for (int i = 0; i < food_times.length; i++){
            pq.add(new Food(food_times[i], i + 1));
        }

        //k 보다 작아야함. 가장 작은것 먹는시간 * 사이클 그래야 하나 날림.
        long sum_time = 0;
        int previous = 0;
        long length = food_times.length;
        //시간을 빼주는 방식이 아니라서 이전에 먹은시간 previous 빼서 다음 음식 다먹을 수 있는지 계산
        while (sum_time + (pq.peek().getTime() - previous) * length <= k){
            int now = pq.poll().getTime();
            sum_time += (now - previous) * length;
            length -= 1;
            previous = now;
        }
        List<Food> collect = pq.stream().sorted(Comparator.comparingInt(Food::getNumber)).collect(Collectors.toList());
        return collect.get((int) ((k - sum_time) % length)).getNumber();
    }

    public static void main(String[] args) {
        int[] food_time = {1, 0, 0, 2};
        System.out.println(new Q6_Answer().solution(food_time,  2));

        food_time = new int[]{8, 6, 4};
        System.out.println(new Q6_Answer().solution(food_time,  15));

        food_time = new int[]{3, 1, 2};
        System.out.println(new Q6_Answer().solution(food_time,  5));

        food_time = new int[]{4, 2, 3, 6, 7, 1, 5, 8};
        System.out.println(new Q6_Answer().solution(food_time,  16));

        food_time = new int[]{4, 2, 3, 6, 7, 1, 5, 8};
        System.out.println(new Q6_Answer().solution(food_time,  27));

        food_time = new int[]{3,1,1,1,2,4,3};
        System.out.println(new Q6_Answer().solution(food_time,  12));

        food_time = new int[]{1, 1, 2, 2, 0};
        System.out.println(new Q6_Answer().solution(food_time,  5));

        food_time = new int[]{7,8,3,3,2,2,2,2,2,2,2,2};
        System.out.println(new Q6_Answer().solution(food_time,  35));
    }


}
