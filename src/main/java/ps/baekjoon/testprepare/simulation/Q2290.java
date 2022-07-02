package ps.baekjoon.testprepare.simulation;

import java.util.Scanner;

/**
 * LCD Test
 * https://www.acmicpc.net/problem/2290
 * <p>
 * --   --        --   --   --   --   --   --
 * |    |    | |  | |    |       | |  | |  | |  |
 * |    |    | |  | |    |       | |  | |  | |  |
 * --   --   --   --   --        --   --
 * | |       |    |    | |  |    | |  |    | |  |
 * | |       |    |    | |  |    | |  |    | |  |
 * --   --        --   --        --   --   --
 * <p>
 * s+2의 가로와 2s+3의 세로
 */
public class Q2290 {
    private static int x;
    private static int y;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int n = sc.nextInt();

        //1아니면 2자리, 1이면 1자리
        String numStr = String.valueOf(n);
        int numLen = numStr.length();

        x = 2 * s + 3;
        y = (s + 2) * numLen + numLen;

        char[][] lcd = new char[x][y];

        for (int i = 0; i < numStr.length(); i++) {
            char c = numStr.charAt(i);

            int start = i * (s + 2 + 1);
            int end = start + (s + 2 + 1);
            if (c == '1') draw1(lcd, s, start, end);
            if (c == '2') draw2(lcd, s, start, end);
            if (c == '3') draw3(lcd, s, start, end);
            if (c == '4') draw4(lcd, s, start, end);
            if (c == '5') draw5(lcd, s, start, end);
            if (c == '6') draw6(lcd, s, start, end);
            if (c == '7') draw7(lcd, s, start, end);
            if (c == '8') draw8(lcd, s, start, end);
            if (c == '9') draw9(lcd, s, start, end);
            if (c == '0') draw0(lcd, s, start, end);
        }


        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(lcd[i][j]);
            }
            System.out.println();
        }
    }

    private static void draw0(char[][] lcd, int s, int start, int end) {
        fillSpace(lcd, start, end);

        drawA(lcd, s, start, end);
        drawB(lcd, s, start, end);
        drawC(lcd, s, start, end);
        drawE(lcd, s, start, end);
        drawF(lcd, s, start, end);
        drawG(lcd, s, start, end);
    }

    private static void draw9(char[][] lcd, int s, int start, int end) {
        fillSpace(lcd, start, end);

        drawA(lcd, s, start, end);
        drawB(lcd, s, start, end);
        drawC(lcd, s, start, end);
        drawD(lcd, s, start, end);
        drawF(lcd, s, start, end);
        drawG(lcd, s, start, end);
    }

    private static void draw8(char[][] lcd, int s, int start, int end) {
        fillSpace(lcd, start, end);

        drawA(lcd, s, start, end);
        drawB(lcd, s, start, end);
        drawC(lcd, s, start, end);
        drawD(lcd, s, start, end);
        drawE(lcd, s, start, end);
        drawF(lcd, s, start, end);
        drawG(lcd, s, start, end);
    }

    private static void draw7(char[][] lcd, int s, int start, int end) {
        fillSpace(lcd, start, end);

        drawA(lcd, s, start, end);
        drawC(lcd, s, start, end);
        drawF(lcd, s, start, end);
    }
    private static void draw6(char[][] lcd, int s, int start, int end) {

        fillSpace(lcd, start, end);

        drawA(lcd, s, start, end);
        drawB(lcd, s, start, end);
        drawD(lcd, s, start, end);
        drawE(lcd, s, start, end);
        drawF(lcd, s, start, end);
        drawG(lcd, s, start, end);
    }

    private static void draw5(char[][] lcd, int s, int start, int end) {

        fillSpace(lcd, start, end);

        drawA(lcd, s, start, end);
        drawB(lcd, s, start, end);
        drawD(lcd, s, start, end);
        drawF(lcd, s, start, end);
        drawG(lcd, s, start, end);
    }

    private static void draw4(char[][] lcd, int s, int start, int end) {

        fillSpace(lcd, start, end);

        drawB(lcd, s, start, end);
        drawC(lcd, s, start, end);
        drawD(lcd, s, start, end);
        drawF(lcd, s, start, end);
    }

    private static void draw3(char[][] lcd, int s, int start, int end) {

        fillSpace(lcd, start, end);

        drawA(lcd, s, start, end);
        drawC(lcd, s, start, end);
        drawD(lcd, s, start, end);
        drawF(lcd, s, start, end);
        drawG(lcd, s, start, end);
    }

    private static void draw2(char[][] lcd, int s, int start, int end) {

        fillSpace(lcd, start, end);

        drawA(lcd, s, start, end);
        drawC(lcd, s, start, end);
        drawD(lcd, s, start, end);
        drawE(lcd, s, start, end);
        drawG(lcd, s, start, end);
    }

    //세로 5 7 9 11 (2S + 3)
    //가로 3 4 5 6 (s + 2)
    private static void draw1(char[][] lcd, int s, int start, int end) {

        fillSpace(lcd, start, end);

        drawC(lcd, s, start, end);
        drawF(lcd, s, start, end);
    }

    /*
      a
     b c
      d
     e f
      g
     */

    private static void drawA(char[][] lcd, int s, int start, int end) {
        for (int i = 0; i < s; i++) {
            lcd[0][start + 1 + i] = '-';
        }
    }

    private static void drawB(char[][] lcd, int s, int start, int end) {
        for (int i = 0; i < s; i++) {
            lcd[1 + i][start] = '|';
        }
    }

    private static void drawC(char[][] lcd, int s, int start, int end) {
        for (int i = 0; i < s; i++) {
            lcd[1 + i][start + s + 1] = '|';
        }
    }

    private static void drawD(char[][] lcd, int s, int start, int end) {
        for (int i = 0; i < s; i++) {
            lcd[s + 1][start + 1 + i] = '-';
        }
    }

    private static void drawE(char[][] lcd, int s, int start, int end) {
        for (int i = 0; i < s; i++) {
            lcd[2 + s + i][start] = '|';
        }
    }

    private static void drawF(char[][] lcd, int s, int start, int end) {
        for (int j = 0; j < s; j++) {
            lcd[s + 2 + j][start + s + 1] = '|';
        }
    }

    private static void drawG(char[][] lcd, int s, int start, int end) {
        for (int i = 0; i < s; i++) {
            lcd[2 * (s + 1)][start + 1 + i] = '-';
        }
    }

    private static void fillSpace(char[][] lcd, int start, int end) {
        for (int i = 0; i < x; i++) {
            for (int j = start; j < end; j++) {
                lcd[i][j] = ' ';
            }
        }
    }
}
