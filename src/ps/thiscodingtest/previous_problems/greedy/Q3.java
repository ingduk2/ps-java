package ps.thiscodingtest.previous_problems.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문자열 뒤집기
 */
public class Q3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        // 그룹 개수 (0의 그룹, 1의 그룹) 최소값
        int sLen = s.length();
        int zeroCnt = 0;
        int oneCnt = 0;

        ////my////
        //first Num
        int prevNum = Character.getNumericValue(s.charAt(0));
        for (int i = 1; i < sLen; i++) {
            int num = Character.getNumericValue(s.charAt(i));
            if (prevNum != num) {
                if (prevNum == 0) {
                    zeroCnt += 1;
                } else {
                    oneCnt += 1;
                }

                prevNum = num;
            }
        }
        //Last Num ++
        if (prevNum == 0) {
            zeroCnt += 1;
        } else {
            oneCnt += 1;
        }

        System.out.println("zeroCnt = " + zeroCnt);
        System.out.println("oneCnt = " + oneCnt);
        System.out.println("result = " + Math.min(zeroCnt, oneCnt));
        ////my////

        ////answer////
        zeroCnt = 0;
        oneCnt = 0;
        if (Character.getNumericValue(s.charAt(0)) == 0) {
            zeroCnt++;
        } else {
            oneCnt++;
        }

        for (int i = 0; i < sLen - 1; i++) {
            int num = Character.getNumericValue(s.charAt(i));
            int numNext = Character.getNumericValue(s.charAt(i + 1));
            if (num != numNext) {
                if (numNext == 1) {
                    oneCnt++;
                } else {
                    zeroCnt++;
                }

            }
        }
        System.out.println("zeroCnt = " + zeroCnt);
        System.out.println("oneCnt = " + oneCnt);
        System.out.println("result = " + Math.min(zeroCnt, oneCnt));
        ////answer////
    }
}
