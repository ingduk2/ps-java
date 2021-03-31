package ps.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 연산자 끼워넣기
 * https://www.acmicpc.net/problem/14888
 *
 * 연산자를 list 로 만들어서 permutation 한 계산식으로 계산
 */
public class Q14888 {

    private static List<Integer> numbers = new ArrayList<>();
    private static int min = Integer.MAX_VALUE;
    private static int max = Integer.MIN_VALUE;

    private static void permutation(boolean[] visited, List<String> operators, String[] output, int depth, int r) {
        if (depth == r) {
            int calcNum = getCalc(output);
            min = Math.min(min, calcNum);
            max = Math.max(max, calcNum);
            return;
        }

        for (int i = 0; i < operators.size(); i++) {
            if (visited[i] == false) {
                visited[i] = true;
                output[depth] = operators.get(i);
                permutation(visited, operators, output, depth + 1, r);
                visited[i] = false;
            }
        }
    }

    private static int getCalc(String[] output) {
        int calcNum = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            int num = numbers.get(i);
            String oper = output[i - 1];
            if (oper.equals("+")) {
                calcNum = calcNum + num;
            } else if (oper.equals("-")) {
                calcNum = calcNum - num;
            } else if (oper.equals("*")) {
                calcNum = calcNum * num;
            } else if (oper.equals("/")) {
                calcNum = calcNum / num;
            }
        }
        return calcNum;
    }

    private static void addOperator(List<String> operatorList, int operator, String s) {
        for (int j = 0; j < operator; j++) {
            operatorList.add(s);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Stream.of(br.readLine().split(" "))
                .map(Integer::parseInt)
                .forEach(e -> {
                    numbers.add(e);
                });

        int[] operators = new int[4];
        String[] inputs = br.readLine().split(" ");
        IntStream.range(0, operators.length)
                .forEach(idx -> {
                    operators[idx] = Integer.parseInt(inputs[idx]);
                });

        List<String> operatorList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                addOperator(operatorList, operators[i], "+");
            } else if (i == 1) {
                addOperator(operatorList, operators[i], "-");
            } else if (i == 2) {
                addOperator(operatorList, operators[i], "*");
            } else if (i == 3) {
                addOperator(operatorList, operators[i], "/");
            }
        }

        boolean[] visited = new boolean[operatorList.size()];
        String[] output = new String[operatorList.size()];
        permutation(visited, operatorList, output, 0, operatorList.size());

        System.out.println(max);
        System.out.println(min);
    }

}
