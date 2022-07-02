package ps.thiscodingtest.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 숫자 카드 게임
 */
public class Q3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        int result = 0;
        while (n-- > 0) {
            line = br.readLine().split(" ");
            int min = Integer.MAX_VALUE;
            for (String s : line) {
                int num = Integer.parseInt(s);
                min = Math.min(min, num);
            }
            result = Math.max(result, min);
        }

        System.out.println("result = " + result);
    }
}
