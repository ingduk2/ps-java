package ps.thiscodingtest.previous_problems.greedy;

import java.util.Arrays;

/**
 * 무지의 먹방 라이브 실패
 */
public class Q6_Fail {

    public int solution(int[] food_times, long k) {
        int index = 0;
        while (k > 0) {
            if (food_times[index] != 0) {
                food_times[index] -= 1;
                k -= 1;
            }

            index ++;
            if (index > food_times.length - 1) {
                index = 0;
            }

            if (Arrays.stream(food_times).sum() == 0){
                return -1;
            }
        }
        System.out.println("index : " + index);
        int answer = 0;
        for(int i = index; i < food_times.length; i++){
            if (food_times[i] != 0){
                answer = i;
                break;
            }
        }
        System.out.println("answer : " + answer);
        return answer + 1;
    }

    public int sol2(int[] food_times, long k) {

        int answer = 0;
        if (Arrays.stream(food_times).sum() <= k){
            return -1;
        }

        int time = 0;
        int index = 0;
        while (true) {
            if (time == k){
                break;
            }
            if (food_times[index] != 0){
                food_times[index] -= 1;
            } else {
                index += 1;
                if (index > food_times.length - 1){
                    index = 0;
                }
                continue;
            }

            index += 1;
            if (index > food_times.length - 1){
                index = 0;
            }
            time ++;
        }

        for(int i=index; i<food_times.length;i++){
            if (food_times[i] != 0){
                answer = i;
                break;
            }
        }
        System.out.println("answer : " + answer);
        return answer + 1;
    }

    public static void main(String[] args) {
        int[] food_time = {1, 0, 0, 2};
        System.out.println(new Q6_Fail().solution(food_time,  2));
        int[] food = {1, 0, 0, 2};
        System.out.println(new Q6_Fail().sol2(food,  2));

        food_time = new int[]{3, 1, 2};
        System.out.println(new Q6_Fail().solution(food_time,  5));
        food = new int[]{3, 1, 2};
        System.out.println(new Q6_Fail().sol2(food,  5));

        food_time = new int[]{4, 2, 3, 6, 7, 1, 5, 8};
        System.out.println(new Q6_Fail().solution(food_time,  16));
        food = new int[]{4, 2, 3, 6, 7, 1, 5, 8};
        System.out.println(new Q6_Fail().sol2(food,  16));

        food_time = new int[]{4, 2, 3, 6, 7, 1, 5, 8};
        System.out.println(new Q6_Fail().solution(food_time,  27));
        food = new int[]{4, 2, 3, 6, 7, 1, 5, 8};
        System.out.println(new Q6_Fail().sol2(food,  27));

        food_time = new int[]{3,1,1,1,2,4,3};
        System.out.println(new Q6_Fail().solution(food_time,  12));
        food = new int[]{3,1,1,1,2,4,3};
        System.out.println(new Q6_Fail().sol2(food,  12));

        food_time = new int[]{1, 1, 2, 2, 0};
        System.out.println(new Q6_Fail().solution(food_time,  5));
        food = new int[]{1, 1, 2, 2, 0};
        System.out.println(new Q6_Fail().sol2(food,  5));

//        [7,8,3,3,2,2,2,2,2,2,2,2] k = 35
        food_time = new int[]{7,8,3,3,2,2,2,2,2,2,2,2};
        System.out.println(new Q6_Fail().solution(food_time,  35));
        food = new int[]{7,8,3,3,2,2,2,2,2,2,2,2};
        System.out.println(new Q6_Fail().sol2(food,  35));
    }


}
