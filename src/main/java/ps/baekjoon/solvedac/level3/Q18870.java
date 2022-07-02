package ps.baekjoon.solvedac.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 좌표 압축
 * https://www.acmicpc.net/problem/18870
 *
 * 2 4 -10 4 -9
 * 정렬 후 중복 제거.
 * -10 -9 2 4 4
 * -10 -9 2 4
 * 위의 배열의 idx
 * 0  1  2  3 (idx)
 * 원래 배열의 숫자를 위의 idx로 치환.
 * 2 3 0 3 1 ??
 *
 * 정렬 후
 *-10 -9 2 4 4
 * 중복 제거하지 말고 map에 하나씩 넣으면서
 * 없을때만 index++
 *
 * 좀 복잡풀이방법..
 * https://codecollector.tistory.com/688
 *
 * 시간초과. 출력 한번에
 */
public class Q18870 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        List<Integer> posList = new ArrayList<>();
        List<Integer> idxList = new ArrayList<>();
        Map<Integer, Integer> idxMap = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            posList.add(num);
            idxList.add(num);
        }

        Collections.sort(idxList);
        AtomicInteger idx = new AtomicInteger();
        for (int i = 0; i < n; i++) {
            Integer num = idxList.get(i);
            idxMap.computeIfAbsent(num, k -> idx.getAndIncrement());
        }

        for (int i = 0; i < n; i++) {
            result.append(idxMap.get(posList.get(i))).append(" ");
        }

        System.out.println(result);

    }
}
