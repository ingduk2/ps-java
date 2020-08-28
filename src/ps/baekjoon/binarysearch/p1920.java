package ps.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p1920 {

    private static int binarysearch(int[] arrN , int target){
        int first = 0;
        int last = arrN.length-1;
        int mid = 0;

        while(first <= last){
            mid = (first + last) / 2;

            if ( target == arrN[mid]){
                return 1;
            } else {
                if( target < arrN[mid]) {
                    last = mid -1;
                } else{
                    first = mid +1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arrN = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int m = Integer.parseInt(br.readLine());
        int[] arrM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arrN);

        for(int i=0; i< m ; i++){
            System.out.println(binarysearch(arrN , arrM[i]));
        }

    }
}
