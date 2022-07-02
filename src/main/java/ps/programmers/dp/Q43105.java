package ps.programmers.dp;

/**
 * 정수 삼각형
 * https://programmers.co.kr/learn/courses/30/lessons/43105
 * dp 문제
 */
public class Q43105 {

    public int solution(int[][] triangle) {
        int answer = 0;

        //d[i][j] = max(d[i][j] + d[i - 1][j] , d[i][j] + d[i - 1][j - 1])
        for (int i = 0; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {

                int up = 0;
                int upLeft = 0;
                if (i - 1 >= 0 && j < triangle[i - 1].length) up = triangle[i][j] + triangle[i - 1][j];
                if (i - 1 >= 0 && j - 1 < triangle[i - 1].length && j - 1 >= 0) upLeft = triangle[i][j] + triangle[i - 1][j - 1];
                triangle[i][j] = Math.max(Math.max(up, upLeft), triangle[i][j]);

                answer = Math.max(triangle[i][j], answer);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] tri = {
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}};
        System.out.println(new Q43105().solution(tri));
    }
}
