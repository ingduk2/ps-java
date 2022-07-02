package ps.thiscodingtest.previous_problems.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 문자열 재정렬
 */
public class Q8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        System.out.println(Arrays.toString(chars));

        int numberSum = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < s.length(); i++){
            boolean digit = Character.isDigit(s.charAt(i));
            if (digit) {
                numberSum += Character.getNumericValue(s.charAt(i));
            } else {
                sb.append(s.charAt(i));
            }
        }

        System.out.println(sb.append(numberSum));
    }
}
