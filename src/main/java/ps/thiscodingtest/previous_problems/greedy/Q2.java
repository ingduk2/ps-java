package ps.thiscodingtest.previous_problems.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 곱하기 혹은 더하기
 */
public class Q2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int strLen = s.length();
        ////my////
        //0 나오면 무시, 나머지는 다 곱한다.
        //1 2 0 4 5
        // 1 * 2  * 4 * 5
        int result = 1;
        for (int i = 0; i < strLen; i++) {
            int num = Character.getNumericValue(s.charAt(i));

            if (num != 0){
                result *= num;
            }
        }
        System.out.println(result);
        ////my////

        ////answer////
        int ret2 = Character.getNumericValue(s.charAt(0));
        for (int i = 1; i < strLen; i++) {
            int num = Character.getNumericValue(s.charAt(i));

            if (num <= 1 || ret2 <= 1) {
                ret2 += num;
            } else {
                ret2 *= num;
            }
        }
        System.out.println("ret2 = " + ret2);
        ////answer////

    }
}
