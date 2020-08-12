package ps.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class p11057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        //d[i][j] = d[i-1][j] + d[i-1][j+2] ...
        //d[i][j] += d[i-1][k] ( j <= k <= 9 )
        int[][] d = new int[n + 1][10];
        for(int i=0; i<=9; i++) d[1][i] = 1;


        for(int i=2; i<=n; i++){
            for(int j=0; j<=9; j++){
                for(int k=j; k<=9; k++){
                    d[i][j] += d[i-1][k] % 10007;
                }
            }
        }

        long ret = 0;
        for( int i=0; i<=9; i++) ret+= d[n][i];
        System.out.println(ret % 10007);
    }
}
