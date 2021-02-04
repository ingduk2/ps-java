package ps.programmers.month_code_challenge1;

import java.util.Arrays;

/**
 * 이진 변환 반복하기
 * https://programmers.co.kr/learn/courses/30/lessons/70129
 */
public class Q70129 {

    public int[] solution(String s) {
        int cnt = 0;
        int removeCnt = 0;

        while (!s.equals("1")) {
            //remove 0
            StringBuilder removedS = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '1') {
                    removedS.append(c);
                } else {
                    removeCnt += 1;
                }
            }

            //length -> bin
            int length = removedS.length();
            StringBuilder binS = new StringBuilder();
            while (length != 0) {
                int rest = length % 2;
                length = length / 2;

                binS.insert(0, rest);
            }

            s = binS.toString();
            cnt += 1;
        }


        return new int[]{cnt, removeCnt};
    }

    public static void main(String[] args) {
        String s = "110010101001";	//[3,8]
        System.out.println(Arrays.toString(new Q70129().solution(s)));

        s = "01110";	//[3,3]
        System.out.println(Arrays.toString(new Q70129().solution(s)));

        s = "1111111";	//[4,1]
        System.out.println(Arrays.toString(new Q70129().solution(s)));
    }
}
