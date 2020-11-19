package ps.programmers.month_code_challenge1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 삼각 달팽이
 */
public class Q68645 {

    public int[] solution(int n) {

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            List<Integer> innerList = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                innerList.add(0);
            }
            list.add(innerList);
        }


        int x = 0;
        int y = 0;
        //          D, R,  U
        int[] dx = {0, 1, -1};
        int[] dy = {1, 0, -1};
        int direction = 0;

        int num = 1;
        for (int i = n; i > 0; i--) {
            for (int j = 0; j < i; j++) {

                list.get(y).set(x, num);
                if (j < i - 1){
                    x += dx[direction % 3];
                    y += dy[direction % 3];
                }
                num += 1;
            }

            direction += 1;
            x += dx[direction % 3];
            y += dy[direction % 3];
        }


        List<Integer> collect = list.stream().flatMap(List::stream).collect(Collectors.toList());
        return collect.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Q68645().solution(4)));
        System.out.println(Arrays.toString(new Q68645().solution(5)));
        System.out.println(Arrays.toString(new Q68645().solution(6)));
    }
}
