package ps.baekjoon.exhaustivesearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int e = Integer.parseInt(line[0]);
        int s = Integer.parseInt(line[1]);
        int m = Integer.parseInt(line[2]);

        int a = 1;
        int b = 1;
        int c = 1;
        int ret = 0;
        for(int i=1; i<=15 * 28 * 19; i++){
            if(a == e && b == s && c == m) {
                ret = i;
                break;
            }

            if( a % 15 == 0) a = 0;
            if( c % 19 == 0) c = 0;
            if( b % 28 == 0) b = 0;
            a += 1;
            b += 1;
            c += 1;

        }

        System.out.println(ret);

    }
}
