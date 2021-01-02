package ps.thiscodingtest.previous_problems.implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 문자열 압축
 */
public class Q9 {

    public int solution(String s) {
        int answer = 0;

        int len = s.length();

        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= len; i++) {
            StringBuilder sb = new StringBuilder();
            String prev = s.substring(0, i);
            int cnt = 1;
            int lastIndex = 0;
            for (int compLen = i; compLen < len; compLen+=i) {
                if (compLen + i <= len) {
                    String next = s.substring(compLen, compLen + i);
                    if (prev.equals(next)){
                        cnt += 1;
                    } else {
                        if (cnt > 1 )sb.append(cnt);
                        sb.append(prev);
                        cnt = 1;
                    }

                    prev = next;
                }
                lastIndex = compLen;
            }
            if (cnt > 1 )sb.append(cnt);
            sb.append(prev);
            if (lastIndex + i > len ) sb.append(s.substring(lastIndex, len));
            System.out.println("result : " + sb + " len : " + sb.length());
            result = Math.min(result, sb.length());
        }
        return result;
    }


    public static void main(String[] args) {
        String s = "aabbaccc";
        System.out.println(new Q9().solution(s));

        s = "ababcdcdababcdcd";
        System.out.println(new Q9().solution(s));

        s = "abcabcdede";
        System.out.println(new Q9().solution(s));

        s = "abcabcabcabcdededededede";
        System.out.println(new Q9().solution(s));

        s = "xababcdcdababcdcd";
        System.out.println(new Q9().solution(s));

        s = "xxx";
        System.out.println(new Q9().solution(s));
    }
}
