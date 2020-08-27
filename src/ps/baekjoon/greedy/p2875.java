package ps.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2875 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int k = Integer.parseInt(line[2]);

        //가능한지
        int result = 0;
        if( n + m > k + 3){
            int teamCnt = 0;
            if( n / m >= 2){
                teamCnt = m;
            } else {
                teamCnt = n / 2;
            }

            for(int i=1; i<=teamCnt; i++){
                if( n + m - i*3 >= k){
                    result = Math.max(result , i);
                }
            }
         }

        System.out.println(result);


    }
}
