package ps.programmers.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 정렬
 * k번째 수
 */
public class Q42748 {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> answer = new ArrayList<>();

        for (int[] c : commands) {
            int i = c[0];
            int j = c[1];
            int k = c[2];

            List<Integer> cutList = new ArrayList<>();
            for (int l = i-1; l < j; l++) {
                cutList.add(array[l]);

            }
            Collections.sort(cutList);
            answer.add(cutList.get(k-1));
        }

        return answer.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        System.out.println(Arrays.toString(new Q42748().solution(array, commands)));
    }
}
