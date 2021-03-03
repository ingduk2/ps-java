package ps.programmers.kakao.intern_2020;

import java.util.*;

/**
 * 수식 최대화
 * https://programmers.co.kr/learn/courses/30/lessons/67257
 */
public class Q67257 {

    private List<List<Integer>> permutations = new ArrayList<>();

    private void permutation(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < r; i++) {
                list.add(output[i]);
            }
            permutations.add(list);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                output[depth] = arr[i];
                permutation(arr, output, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }

    private long calulate(List<String> expressions, List<List<String>> operatorsList) {
        long max = 0;
        for (List<String> operators : operatorsList) {
            List<String> copyExpressions = new ArrayList<>(expressions);
            long calcVal = 0;
            for (String oper : operators) {
                int i = 0;
                while (copyExpressions.size() > 0 && copyExpressions.size() > i) {
                    String next = copyExpressions.get(i);
                    if (next.equals(oper)) {
                        long a = Long.parseLong(copyExpressions.get(i - 1));
                        long b = Long.parseLong(copyExpressions.get(i + 1));

                        long num = 0;
                        switch (oper) {
                            case "+":
                                num = a + b;
                                break;
                            case "-":
                                num = a - b;
                                break;
                            case "*":
                                num = a * b;
                                break;
                            case "/":
                                num = a / b;
                                break;
                        }
                        copyExpressions.remove(i--);
                        copyExpressions.remove(i);
                        copyExpressions.remove(i);
                        copyExpressions.add(i , String.valueOf(num));
                        calcVal = num;
                        i = 0;
                        continue;
                    }
                    i++;
                }
            }
            max = Math.max(max, Math.abs(calcVal));
        }
        return max;
    }

    private List<List<String>> getOperatorsList(List<String> operators) {
        int[] arr = new int[operators.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        int[] output = new int[arr.length];
        boolean[] visited = new boolean[arr.length];
        permutation(arr, output, visited, 0, arr.length, arr.length);


        List<List<String>> operatorsList = new ArrayList<>();
        for (List<Integer> permutation : permutations) {
            List<String> operList = new ArrayList<>();
            for (Integer i : permutation) {
                operList.add(operators.get(i));
            }
            operatorsList.add(operList);
        }

        return operatorsList;
    }


    public long solution(String expression) {
        List<String> expressions = new ArrayList<>();
        Set<String> operators = new HashSet<>();
        String num = "";
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                num += c;
            } else {
                expressions.add(num);
                expressions.add(String.valueOf(c));
                num = "";
                operators.add(String.valueOf(c));
            }
        }
        expressions.add(num);

        List<List<String>> operatorsList = getOperatorsList(new ArrayList<>(operators));

        return calulate(expressions, operatorsList);
    }

    public static void main(String[] args) {
        String expression = "100-200*300-500+20";
//        60420
        System.out.println(new Q67257().solution(expression));

        expression = "50*6-3*2";
        System.out.println(new Q67257().solution(expression));
//        300
    }
}
