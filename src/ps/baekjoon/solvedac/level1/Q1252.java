package ps.baekjoon.solvedac.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 이진수 덧셈
 * https://www.acmicpc.net/problem/1252
 *
 * 0으로 시작 가능.
 * 결과는 0인 경우 제외 1로 시작.
 *
 * 둘중에 더 길이가 긴 것으로 0붙여줄려다가
 * 그냥 int[] 배열을 길이 긴 것으로 만들면 0이 들어가있음.
 *
 * 111
 * 111
 * 0111
 *
 * 101
 * 101
 * 0101
 *
 * -80자리까지 가능해서 마지막에 int로 바꾸면 NumberFormatException.
 *
 * -0000 0001
 * 0001 앞에 0처리 필요
 *
 * -00000 00000
 * 000000 나옴..
 *
 * - BigInteger 도 가능.
 */
public class Q1252 {

    //1234, 123 -> 4321, 3210
    //123, 1234 -> 0123, 4321
    //123, 123 -> 321, 321
    private static List<String> makeBinaryNumber(String n, String m) {
        List<String> numbers = new ArrayList<>();

        //reverse
        int nLen = n.length();
        StringBuilder rN = new StringBuilder();
        for (int i = nLen - 1; i >= 0; i--) {
            rN.append(n.charAt(i));
        }

        int mLen = m.length();
        StringBuilder rM = new StringBuilder();
        for (int i = mLen - 1; i >= 0; i--) {
            rM.append(m.charAt(i));
        }

        //add zero
        if (nLen > mLen) {
            rM.append("0".repeat(nLen - mLen));
        } else if (nLen < mLen) {
            rN.append("0".repeat(mLen - nLen));
        }

        numbers.add(rN.toString());
        numbers.add(rM.toString());
        return numbers;
    }

    public static void main(String[] args) {

        Input in = new Input();
        String n = in.next();
        String m = in.next();

        List<String> numbers = makeBinaryNumber(n, m);
        n = numbers.get(0);
        m = numbers.get(1);

        int len = n.length();
        StringBuilder sumStr = new StringBuilder();
        int carry = 0;

        for (int i = 0; i < len; i++) {
            int num1 = Character.getNumericValue(n.charAt(i));
            int num2 = Character.getNumericValue(m.charAt(i));

            int sum = num1 + num2 + carry;
            if (sum <= 1) {
                sumStr.append(sum);
                carry = 0;
            } else {
                sumStr.append(sum - 2);
                carry = 1;
            }
        }

        sumStr.append(carry);
        String result = sumStr.reverse().toString();

        int sum = 0;
        int startIdx = 0;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '1' && sum == 0) {
                startIdx = i;
            }
            sum += Character.getNumericValue(result.charAt(i));
        }

        if (sum == 0) {
            System.out.println(0);
        } else {
            for (int i = startIdx; i < result.length(); i++) {
                System.out.print(result.charAt(i));
            }
        }

    }

    private static class Input {
        private BufferedReader br;
        private StringTokenizer st;

        public Input() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        private String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public String line() throws IOException {
            return br.readLine();
        }
    }
}

