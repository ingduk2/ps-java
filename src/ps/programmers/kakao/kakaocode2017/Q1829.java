package ps.programmers.kakao.kakaocode2017;

import java.util.Arrays;

/**
 * 카카오프렌즈 컬러링북
 * https://programmers.co.kr/learn/courses/30/lessons/1829
 */
public class Q1829 {

    private boolean[][] visited;

    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, 1,  0, -1};

    private int picX;
    private int picY;

    private int dfs(int x, int y, int[][] picture) {
        int sum = 1;
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < picX && ny >= 0 && ny < picY) {
                if (picture[x][y] == picture[nx][ny] && visited[nx][ny] == false) {
                    sum += dfs(nx, ny, picture);
                }
            }
        }
        return sum;
    }

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        picX = picture.length;
        picY = picture[0].length;

        visited = new boolean[picX][picY];

        for (int i = 0; i < picX; i++) {
            for (int j = 0; j < picY; j++) {
                if (picture[i][j] != 0 && visited[i][j] == false) {
                    numberOfArea ++;
                    maxSizeOfOneArea = Math.max(dfs(i, j, picture), maxSizeOfOneArea);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public static void main(String[] args) {
        int m = 6;
        int n = 4;

        int[][] picture = {
                {1, 1, 1, 0},
                {1, 2, 2, 0},
                {1, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 3},
                {0, 0, 0, 3}};

        //4, 5
        System.out.println(Arrays.toString(new Q1829().solution(m, n, picture)));


        m = 13;
        n = 16;
        picture = new int[][]{{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 0},{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 0}, {0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0},{0, 0, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(new Q1829().solution(m, n, picture)));

    }
}
