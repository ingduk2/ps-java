package ps.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);

        List<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++)
            list.add(Integer.parseInt(br.readLine()));

        int resultCnt = 0;
        for(int i=n-1; i>=0; i--){
            int nam = k/list.get(i);
            if(nam > 0){
                resultCnt += nam;
                k -= nam * list.get(i);
            }
        }

        System.out.println(resultCnt);
    }

}
