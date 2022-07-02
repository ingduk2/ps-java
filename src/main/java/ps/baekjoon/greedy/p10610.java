package ps.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p10610 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();

        String result = "-1";

        int eachSum = 0;
        for(int i=0; i<n.length(); i++){
            int eachNumber = Character.getNumericValue(n.charAt(i));
            eachSum += eachNumber;
        }

        char[] arr = n.toCharArray();
        Arrays.sort(arr);

        if(eachSum % 3 == 0
            && arr[0] == '0'){

            result = "";
            for(int i = arr.length-1; i >= 0; i--){
                result += arr[i];
            }
        }

        System.out.println(result);

    }
}
