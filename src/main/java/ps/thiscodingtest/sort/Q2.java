package ps.thiscodingtest.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 위에서 아래로
 */
public class Q2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int n = Integer.parseInt(line);
        List<Integer> list = new ArrayList<>();
        while (n-- > 0) {
            list.add(Integer.parseInt(br.readLine()));
        }

//        Collections.sort(list, Collections.reverseOrder());
//        System.out.println(list);

        list.stream().sorted(Comparator.reverseOrder()).forEach(num -> System.out.print(num + " "));
    }
}
