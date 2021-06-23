package ps.baekjoon.solvedac.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 집합
 * https://www.acmicpc.net/problem/11723
 *
 * 그냥 arrayList 버전.
 * 출력 속도때문에 모아서 출력.
 *
 * 여기서는 집합이라 값이 1개씩밖에 없어서 처음에 for i 로 remove 했었는데
 * 만약에 값 여러개면 올바른 답이 나오지 않았을거임 그래서 iterator로 교체함.
 *
 * intellij review 보니 removeIf 쓰래서 변경.
 */
public class Q11723 {

    private static List<Integer> list = new ArrayList<>();

    private static void remove(int num) {
        list.removeIf(next -> next == num);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int m = Integer.parseInt(br.readLine());

        List<Integer> allList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            allList.add(i);
        }

        for (int i = 0; i < m; i++) {
            String[] lines = br.readLine().split(" ");
            String cmd = lines[0];
            int num = 0;
            if (lines.length > 1) {
                num = Integer.parseInt(lines[1]);
            }

            switch (cmd) {
                case "add":
                    list.add(num);
                    break;
                case "remove":
                    remove(num);
                    break;
                case "check":
                    if (list.contains(num)) {
                        result.append(1).append("\n");
                    } else {
                        result.append(0).append("\n");
                    }
                    break;
                case "toggle":
                    if (list.contains(num)) {
                        remove(num);
                    } else {
                        list.add(num);
                    }
                    break;
                case "all":
                    list = new ArrayList<>(allList);
                    break;
                case "empty":
                    list.clear();
                    break;
            }

        }
        System.out.println(result);
    }

}
