package ps.baekjoon.testprepare.partBasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 감소하는 수
 * https://www.acmicpc.net/problem/1038수
 *
 * 9876543210 이 가장 큰수..
 *
 * 실패
 * 0부터 숫자 하나씩 + 하면서 자리수마다 54321 이면 1, 2, 3 오름인지 검사하는 방법.
 * 시간초과 -> 9876543210 까지 간다면 이미 98억이기 때문에 엄청난 시간 소요. (1억에 1초.)
 *
 * 새 접근
 * 0 1 2 3 4 5 6 7 8 9 배열 시작
 * 1 부터 차례대로 붙여본다. (감소하는 수가 안되면 다음 수를 붙여본다.)
 * 10 11(X) 안되면 2로 넘어간다.
 * 20 21 22(X) 3으로 넘어간다.
 * 30 31 32 33(X) 4...
 * 9 까지 한다음 새로 만들어진
 * 10 11 20 21 30 ... 98 열에
 * 1 부터 차례대로 붙여서 감소하는 수 만듬반복.
 */
public class Q1038 {
    public static void main(String[] args) {

        Input input = new Input();
        int n = input.nextInt();

        List<String> numbers = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        if (n < 10) {
            System.out.println(numbers.get(n));
            return;
        }

        //앞에서 0 ~ 9 사용
        int cnt = numbers.size() - 1;

        String maxDescNum = "9876543210";
        String maxNum = "0";

        List<String> nextNumbers = new ArrayList<>();
        while (true) {

            if (Long.parseLong(maxNum) >= Long.parseLong(maxDescNum)) {
                System.out.println(-1);
                return;
            }

            for (int i = 1; i < 10; i++) {
                for (String number : numbers) {
                    //붙일 숫자가 크면
                    if (i > Character.getNumericValue(number.charAt(0))) {
                        //n번째 있으면
                        if (++cnt == n) {
                            System.out.println(i + number);
                            return;
                        }
                        maxNum = i + number;
                        nextNumbers.add(i + number);
                    }
                    //붙일 숫자가 작거나 같으믄
                    else {
                        break;
                    }
                }
            }

            numbers = new ArrayList<>(nextNumbers);
            nextNumbers.clear();
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
    }
}

