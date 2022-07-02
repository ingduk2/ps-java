package ps.thiscodingtest.previous_problems.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 럭키 스트레이트
 */
public class Q7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();

        int sum1 = 0;
        int sum2 = 0;
        for (int i=0; i < num.length(); i++){
            int numericValue = Character.getNumericValue(num.charAt(i));
            if (i < num.length() / 2){
                sum1 += numericValue;
            } else {
                sum2 += numericValue;
            }
        }

        if (sum1 == sum2){
            System.out.println("LUCKY");
        } else {
            System.out.println("READY");
        }
    }
}
