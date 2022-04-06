package ps.programmers.kakao.blind2021;

import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

/**
 * 메뉴 리뉴얼
 * https://programmers.co.kr/learn/courses/30/lessons/72411
 *
 * for course
 *  course 당 손님별로 조합.
 *      조합에서 max 카운트인 것들을 반환
 *      max 가 1인 것은 무시.
 *      3번의 예제에서 WX, XW 가 같은 것이므로, 조합에서 카운팅시 정렬필요.
 * result 오름차순
 */
public class Q72411 {

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        for (int c : course) {
            Map<String, LongAdder> orderCountMap = new HashMap<>();
            for (String order : orders) {
                countOrderByCombination(c, orderCountMap, order);
            }
            answer.addAll(getCourseMenus(orderCountMap));
        }

        Collections.sort(answer);
        return answer.toArray(new String[0]);
    }

    private List<String> getCourseMenus(Map<String, LongAdder> orderCountMap) {
        List<String> result = new ArrayList<>();
        if (orderCountMap.isEmpty()) return result;

        List<Map.Entry<String, LongAdder>> orderCountAsc = orderCountMap.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(
                        (Map.Entry<String, LongAdder> e) -> e.getValue().intValue()).reversed())
                .collect(Collectors.toList());

        int maxCnt = orderCountAsc.get(0).getValue().intValue();

        if (maxCnt <= 1) return result;

        return orderCountAsc.stream()
                .filter(map -> map.getValue().intValue() >= maxCnt)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private void countOrderByCombination(int c, Map<String, LongAdder> orderCountMap, String order) {
        char[] orderChars = order.toCharArray();

        int n = orderChars.length;
        boolean[] visited = new boolean[n];
        combination(orderChars, visited, 0, n, c, orderCountMap);
    }

    public void combination(
            char[] arr,
            boolean[] visited,
            int start, int n, int r,
            Map<String, LongAdder> orderCountMap) {

        if (r == 0) {
            countOrder(arr, visited, n, orderCountMap);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n , r - 1, orderCountMap);
            visited[i] = false;
        }
    }

    private void countOrder(char[] arr, boolean[] visited, int n, Map<String, LongAdder> orderCountMap) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (visited[i]) sb.append(arr[i]);
        }

        char[] chars = sb.toString().toCharArray();
        Arrays.sort(chars);
        String key = new String(chars);

        orderCountMap.computeIfAbsent(key, value -> new LongAdder())
                .increment();
    }

    public static void main(String[] args) {
        String[] solution = new Q72411().solution(
                new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4});
        System.out.println(Arrays.toString(solution));

        solution = new Q72411().solution(
                new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[]{2, 3, 5});
        System.out.println(Arrays.toString(solution));

        solution = new Q72411().solution(
                new String[]{"XYZ", "XWY", "WXA"}, new int[]{2, 3, 4});
        System.out.println(Arrays.toString(solution));
    }
}
