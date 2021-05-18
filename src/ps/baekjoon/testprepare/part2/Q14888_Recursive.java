package ps.baekjoon.testprepare.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 연산자 끼워넣기
 * https://www.acmicpc.net/problem/14888
 *
 * 1. 연산자를 list 로 만들어서 permutation 한 계산식으로 계산
 * 2. 재귀로 단순하게
 */
public class Q14888_Recursive {

    private static List<Integer> numbers = new ArrayList<>();
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;
    private static int sum = 0;

    private static void calculate(int plus, int minus, int mul, int div, int n, int idx, int ret) {

        if (idx == n) {
            min = Math.min(min, ret);
            max = Math.max(max, ret);
            return;
        }

        if (plus > 0) {
            calculate(plus - 1, minus, mul, div, n, idx + 1, ret + numbers.get(idx));
        }
        if (minus > 0) {
            calculate(plus, minus - 1, mul, div, n, idx + 1, ret - numbers.get(idx));
        }
        if (mul > 0) {
            calculate(plus, minus, mul - 1, div, n, idx + 1, ret * numbers.get(idx));
        }
        if (div > 0) {
            calculate(plus, minus, mul, div - 1, n, idx + 1, ret / numbers.get(idx));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());

        numbers = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> operators = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            operators.add(Integer.parseInt(st.nextToken()));
        }

        calculate(operators.get(0), operators.get(1), operators.get(2), operators.get(3), n, 1, numbers.get(0));
        System.out.println(max);
        System.out.println(min);
    }



}
