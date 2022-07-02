package ps.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        int[] d = new int[11];
        d[0] = 1;
        d[1] = 1;
        d[2] = 2;
        for( int i = 3; i <= 10; i++){
            d[i] = d[i-1] + d[i-2] + d[i-3];
        }

        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());
            System.out.println(d[n]);
        }
    }
}
