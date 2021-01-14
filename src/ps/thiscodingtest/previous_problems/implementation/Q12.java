package ps.thiscodingtest.previous_problems.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 기둥과 보 설치
 * https://programmers.co.kr/learn/courses/30/lessons/60061?language=java
 * 한 좌표에 기둥, 보 둘다 설치가능
 * 삭제시 4방향만 검사했는데  다른 영향받는 부분이 있을 수 있음
 * 양쪽 끝 부분 보 연결에서 x < length - 1 땜에 계속 싪패함.
 * 아니고 delete 하는 부분 이중for문 안에서 실패하면 원래 값으로 바꾸게했는데
 * 이부분이 잘못되었음.. 저렇게하면 기둥, 보가 뒤바뀔수가 있음, 밖에서하면
 * 이게 보인지 아닌지 구분이 가는데, for문안에서는 보여야하지만 기둥을 원래대로 변경하는 경우가 생기게됨.
 * 밖에서 원래값으로 처리하는것으로 바꾸어야됨.
 */
public class Q12 {

    private int[][] kidungArr;
    private int[][] boArr;
    private int length;

    private boolean isInstallKidung(int x, int y) {
        // 바닥 위
        if (y == 0) {
            return true;
        }
        //보의 한쪽 끝 부분 위
        if ((x > 0 && boArr[x - 1][y] == 1) || (boArr[x][y] == 1)) {
            return true;
        }
        // 다른 기둥 위 맨위 설치 안되게 해도 정답 가능.
        if (y > 0 && y < length - 1 && kidungArr[x][y - 1] == 0) {
            return true;
        }

        return false;
    }

    private boolean isInstallBo(int x, int y) {
        //보는 한쪽 끝 부분이 기둥 위 , 맨 오른쪽은 안되게함.
        if ((y > 0  && x < length - 1 &&kidungArr[x][y - 1] == 0) || (x < length - 1 &&  y > 0 && kidungArr[x + 1][y - 1] == 0)) {
            return true;
        }
        //양쪽 끝 부분이 다른 보와 동시에 연결
        if (((x > 0 && boArr[x - 1][y] == 1) && (x < length - 1 && boArr[x + 1][y] == 1))) {
//        if (x > 0 && boArr[x - 1][y] == 1 && boArr[x + 1][y] == 1) {
            return true;
        }

        return false;
    }

    private boolean isDelete(int x, int y, int kind) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (kidungArr[i][j] == 0 && !isInstallKidung(i, j)) {
                    return false;
                }

                if (boArr[i][j] == 1 && !isInstallBo(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] solution(int n, int[][] build_frame) {

        /*
기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
         */
        kidungArr = new int[n + 1][n + 1];
        boArr = new int[n + 1][n + 1];
        length = n + 1;
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                kidungArr[i][j] = 2;
                boArr[i][j] = 2;
            }
        }


        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int a = build_frame[i][2]; // 0 기둥, 1 보
            int b = build_frame[i][3]; // 0 삭제, 1 설치

            if (b == 0) {
                if (a == 0) kidungArr[x][y] = 2;
                if (a == 1) boArr[x][y] = 2;
                if (!isDelete(x, y, a)) {
                    if (a == 0) kidungArr[x][y] = 0;
                    if (a == 1) boArr[x][y] = 1;
                }
            }

            if (b == 1) {
                if (a == 0) {
                    if (isInstallKidung(x, y)) {
                        kidungArr[x][y] = 0;
                    }
                }
                if (a == 1) {
                    if (isInstallBo(x, y)) {
                        boArr[x][y] = 1;
                    }
                }
            }

        }

        List<int[]> answer = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (kidungArr[i][j] == 0) {
                    answer.add(new int[]{i, j, 0});
                }
                if (boArr[i][j] == 1) {
                    answer.add(new int[]{i, j, 1});
                }
            }
        }

        return answer.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] build_frame = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};
        int[][] solution = new Q12().solution(n, build_frame);
        for (int[] ints : solution) {
            System.out.println(Arrays.toString(ints));
        }


        System.out.println();
        n = 5;
        build_frame = new int[][]{{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}};
        solution = new Q12().solution(n, build_frame);
        for (int[] ints : solution) {
            System.out.println(Arrays.toString(ints));
        }


        System.out.println();
        n = 5;
        build_frame = new int[][]{
                {0, 0, 0, 1},
                {0, 1, 0, 1},
                {0, 2, 0, 1},
                {0, 3, 0, 1},
                {0, 4, 0, 1},
                {0, 5, 0, 1},
//                {0, 0, 1, 1},
                {0, 1, 1, 1},
                {1, 2, 1, 1},
                {1, 3, 1, 1},
                {5, 0, 0, 1},
                {4, 1, 1, 1},
                {5, 1, 1, 1},

        };
        solution = new Q12().solution(n, build_frame);
        for (int[] ints : solution) {
            System.out.println(Arrays.toString(ints));
        }
        /*
[1, 0, 0]
[1, 1, 1]
[2, 1, 0]
[2, 2, 1]
[3, 2, 1]
[4, 2, 1]
[5, 0, 0]
[5, 1, 0]

[0, 0, 0]
[0, 1, 1]
[1, 1, 1]
[2, 1, 1]
[3, 1, 1]
[4, 0, 0]
         */
    }
}
