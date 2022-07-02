package ps.programmers.kakao.bilnd2022;

import java.util.*;

/*
신고 결과 받기
- 신고 횟수를 센다. (신고당한 사람 key) (result 기준으로 세면 중복카운트)
1. Map<String, Set<String> 에 넣는다. (그러면 중복 신고가 처리됨)
2. 1번 map으로 신고 횟수를 센다
3. 메일을 받는 횟수는 setMap 을 돌면서 신고 횟수가 k 이상이면 +1
 */
public class Q92334 {

    private Map<String, Set<String>> getSingoSetMap(String[] report) {
        Map<String, Set<String>> map = new HashMap<>();

        for (String r : report) {
            String[] s = r.split(" ");
            String s1 = s[0];
            String s2 = s[1];

            if (map.containsKey(s1)) {
                map.get(s1).add(s2);
            } else {
                Set<String> set = new HashSet<>();
                set.add(s2);
                map.put(s1, set);
            }
        }
        return map;
    }

    private Map<String, Integer> getSingoCountMap(Map<String, Set<String>> report) {
        Map<String, Integer> singoCountMap = new HashMap<>();
        for (Set<String> value : report.values()) { // stream flatmap
            for (String v : value) {
                if (singoCountMap.containsKey(v)) {
                    singoCountMap.put(v, singoCountMap.get(v) + 1);
                } else {
                    singoCountMap.put(v, 1);
                }
            }
        }
        return singoCountMap;
    }

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Map<String, Set<String>> singoSetMap = getSingoSetMap(report);
        Map<String, Integer> singoCountMap = getSingoCountMap(singoSetMap);

        for (Map.Entry<String, Set<String>> stringSetEntry : singoSetMap.entrySet()) {
            Set<String> singoIDs = stringSetEntry.getValue();
            for (String singoID : singoIDs) {
                if (singoCountMap.get(singoID) >= k) {
                    String key = stringSetEntry.getKey();
                    for (int i = 0; i < id_list.length; i++) {
                        if (id_list[i].equals(key)) {
                            answer[i] += 1;
                        }
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] solution1 = new Q92334().solution(
                new String[]{"muzi", "frodo", "apeach", "neo"},
                new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"},
                2);
        System.out.println(Arrays.toString(solution1));

        int[] solution2 = new Q92334().solution(
                new String[]{"con", "ryan"},
                new String[]{"ryan con", "ryan con", "ryan con", "ryan con"},
                3);
        System.out.println(Arrays.toString(solution2));
    }
}
