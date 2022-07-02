package ps.thiscodingtest.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 왕실의 나이트
 */
public class Q2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int x = Character.getNumericValue(input.charAt(1));
        System.out.println("x = " + x);

        int y = input.charAt(0) - 'a' + 1;
        System.out.println("y = " + y);

        int[][] moves = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                        {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

        int ret = 0;
        for (int[] move : moves) {
            int nx = x + move[0];
            int ny = y + move[1];
            if (nx >= 1 && nx <= 8 && ny >= 1 && ny <= 8) {
                ret += 1;
            }
        }
        System.out.println("ret = " + ret);
    }
}
