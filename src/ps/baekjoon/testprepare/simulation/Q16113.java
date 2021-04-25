package ps.baekjoon.testprepare.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시그널
 * https://www.acmicpc.net/problem/16113
 *
 * 전체길이 / 5 로 배열 돌림
 * x 세로 다 채우고 + 1, y 0~ 세로 개수까지 반복
 * arr[i / (n / 5)][i % (n / 5)] = c;
 *
 * 빈공간이 아니면
 * char[][] num, 5, 3 짜리 배열에 # 복사.
 * 빈공간이 나오면 위의 숫자배열 몇인지 확인.
 *
 * 마지막 빈배열 체크.
 */
public class Q16113 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        char[][] arr = new char[5][n / 5];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //x 세로 다 채우고 + 1, y 0~ 세로 개수까지 반복
            arr[i / (n / 5)][i % (n / 5)] = c;
        }

        char[][] number = new char[5][3];

        int colCnt = 0;

        for (int y = 0; y < n / 5; y++) {
            if (!checkWhiteSpace(arr, y)) {
                //copy no whiteSpace
                for (int i = 0; i < 5; i++) {
                    number[i][colCnt] = arr[i][y];
                }

                colCnt ++;
            } else {
                if (colCnt != 0) {
                    System.out.print(findNumber(number));
                }

                colCnt = 0;
                number = new char[5][3];
            }
        }

        if (!checkEmptyArray(number)) {
            System.out.print(findNumber(number));
        }

    }

    private static boolean checkEmptyArray(char[][] number) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                if (number[i][j] != '\0') {
                    return false;
                }
            }
        }

        return true;
    }

    private static int findNumber(char[][] number) {
        if (findOne(number)) return 1;
        if (findTwo(number)) return 2;
        if (findThree(number)) return 3;
        if (findFour(number)) return 4;
        if (findFive(number)) return 5;
        if (findSix(number)) return 6;
        if (findSeven(number)) return 7;
        if (findEight(number)) return 8;
        if (findNine(number)) return 9;
        if (findZero(number)) return 0;

        return 0;
    }

    private static boolean findZero(char[][] number) {
        char[][] zero = {
                {'#', '#', '#'},
                {'#', '.', '#'},
                {'#', '.', '#'},
                {'#', '.', '#'},
                {'#', '#', '#'}
        };

        return isSameNumber(number, zero);
    }

    private static boolean findNine(char[][] number) {
        char[][] nine = {
                {'#', '#', '#'},
                {'#', '.', '#'},
                {'#', '#', '#'},
                {'.', '.', '#'},
                {'#', '#', '#'}
        };

        return isSameNumber(number, nine);
    }

    private static boolean findEight(char[][] number) {
        char[][] eight = {
                {'#', '#', '#'},
                {'#', '.', '#'},
                {'#', '#', '#'},
                {'#', '.', '#'},
                {'#', '#', '#'}
        };

        return isSameNumber(number, eight);
    }

    private static boolean findSeven(char[][] number) {
        char[][] seven = {
                {'#', '#', '#'},
                {'.', '.', '#'},
                {'.', '.', '#'},
                {'.', '.', '#'},
                {'.', '.', '#'}
        };

        return isSameNumber(number, seven);
    }

    private static boolean findSix(char[][] number) {
        char[][] six = {
                {'#', '#', '#'},
                {'#', '.', '.'},
                {'#', '#', '#'},
                {'#', '.', '#'},
                {'#', '#', '#'}
        };

        return isSameNumber(number, six);
    }

    private static boolean findFive(char[][] number) {
        char[][] five = {
                {'#', '#', '#'},
                {'#', '.', '.'},
                {'#', '#', '#'},
                {'.', '.', '#'},
                {'#', '#', '#'}
        };

        return isSameNumber(number, five);
    }

    private static boolean findFour(char[][] number) {
        char[][] four = {
                {'#', '.', '#'},
                {'#', '.', '#'},
                {'#', '#', '#'},
                {'.', '.', '#'},
                {'.', '.', '#'}
        };

        return isSameNumber(number, four);
    }

    private static boolean findThree(char[][] number) {
        char[][] three = {
                {'#', '#', '#'},
                {'.', '.', '#'},
                {'#', '#', '#'},
                {'.', '.', '#'},
                {'#', '#', '#'}
        };

        return isSameNumber(number, three);
    }

    private static boolean findTwo(char[][] number) {
        char[][] two = {
                {'#', '#', '#'},
                {'.', '.', '#'},
                {'#', '#', '#'},
                {'#', '.', '.'},
                {'#', '#', '#'}
        };

        return isSameNumber(number, two);
    }

    private static boolean findOne(char[][] number) {
        char[][] one = {
                {'#', '\0', '\0'},
                {'#', '\0', '\0'},
                {'#', '\0', '\0'},
                {'#', '\0', '\0'},
                {'#', '\0', '\0'}
        };

        return isSameNumber(number, one);
    }

    private static boolean isSameNumber(char[][] a, char[][] b) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkWhiteSpace(char[][] arr, int y) {
        for (int x = 0; x < 5; x++) {
            if (arr[x][y] != '.') {
                return false;
            }
        }
        return true;
    }


}
