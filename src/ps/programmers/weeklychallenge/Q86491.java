package ps.programmers.weeklychallenge;

/**
 * 최소직사각형
 * https://programmers.co.kr/learn/courses/30/lessons/86491?language=java
 *
 * 1. 돌릴 수 있기 때문에 가로, 세로의 의미가 없다.
 * 2. for 순회하면서 {a, b} a, b 중 더 큰 수 중 최대값.
 * 3. a, b 중 더 작은 수 중 최대값
 * 4, 2, 3의 최대값이 최소 직사각형값이 된다.
 */
public class Q86491 {

    public int solution(int[][] sizes) {
        int maxBig = Integer.MIN_VALUE;
        int maxSmall = Integer.MIN_VALUE;

        for (int[] size : sizes) {
            int w = size[0];
            int h = size[1];

            if (w >= h) {
                maxBig = Math.max(maxBig, w);
                maxSmall = Math.max(maxSmall, h);
            } else {
                maxBig = Math.max(maxBig, h);
                maxSmall = Math.max(maxSmall, w);
            }
        }

        return maxBig * maxSmall;
    }

    public static void main(String[] args) {
        int solution = new Q86491().solution(new int[][]{
                {60, 50},
                {30, 70},
                {60, 30},
                {80, 40}
        });
        System.out.println(solution);

        int solution1 = new Q86491().solution(new int[][]{
                {10, 7},
                {12, 3},
                {8, 15},
                {14, 7},
                {5, 15}
        });
        System.out.println(solution1);

        int solution2 = new Q86491().solution(new int[][]{
                {14, 4},
                {19, 6},
                {6, 16},
                {18, 7},
                {7, 11}
        });
        System.out.println(solution2);
    }
}
