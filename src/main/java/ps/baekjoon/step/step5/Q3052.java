package ps.baekjoon.step.step5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 나머지
 * https://www.acmicpc.net/problem/3052
 */
public class Q3052 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Integer> remainderSet = new HashSet<>();
        for (int i = 0; i < 10; i++) {
             int input = Integer.parseInt(br.readLine());
             remainderSet.add(input % 42);
        }
        System.out.println(remainderSet.size());
    }


}
