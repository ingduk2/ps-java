package ps.programmers.kakao.blind2018;

import java.util.*;

/**
 * [1차] 뉴스 클러스터링
 * https://programmers.co.kr/learn/courses/30/lessons/17677
 */
public class Q17677 {


    private Map<String, Integer> getTwoSubset(String str) {
        Map<String, Integer> cntMap = new HashMap<>();

        for (int i = 0; i < str.length() - 1; i++) {
            String substring = str.substring(i, i + 2);
            if (Character.isAlphabetic(substring.charAt(0)) && Character.isAlphabetic(substring.charAt(1))) {
//                cntMap.computeIfPresent(substring, (key, cnt) -> cnt + 1);
//                cntMap.computeIfAbsent(substring, (key) -> 1);
                cntMap.merge(substring, 1, (value, putValue) -> value + 1);
            }
        }
        return cntMap;
    }

    private int getIntersectionCnt(Map<String, Integer> subset1, Map<String, Integer> subset2) {
        int cnt = 0;

        for (String key : subset1.keySet()) {
            if (subset2.containsKey(key)) {
                cnt += Math.min(subset1.get(key), subset2.get(key));
            }
        }

        return cnt;
    }

    private int getUnionCnt(Map<String, Integer> subset1, Map<String, Integer> subset2) {
        Map<String, Integer> unionMap = new HashMap<>();
        subset1.forEach(unionMap::put);

//        subset2.forEach((key, value) -> {
//            unionMap.merge(key, value, (oldVal, newVal) -> Math.max(oldVal, newVal));
//        });
        subset2.forEach((key, value) -> unionMap.merge(key, value, Math::max));

        return unionMap.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int solution(String str1, String str2) {
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        Map<String, Integer> twoSubset1 = getTwoSubset(str1);
        Map<String, Integer> twoSubset2 = getTwoSubset(str2);

        int intersectionCnt = getIntersectionCnt(twoSubset1, twoSubset2);
        int unionCnt = getUnionCnt(twoSubset1, twoSubset2);

        double jakad;
        if (intersectionCnt == 0 && unionCnt == 0) {
            jakad = 1;
        } else {
            jakad = (double) intersectionCnt / (double) unionCnt;
        }

        return (int) (jakad * 65536);
    }

    public static void main(String[] args) {
        String str1 = "FRANCE";
        String str2 = "french";//	16384
        System.out.println(new Q17677().solution(str1, str2));

        str1 = "handshake";
        str2 = "shake hands";//	65536
        System.out.println(new Q17677().solution(str1, str2));

        str1 = "aa1+aa2";
        str2 = "AAAA12";//	43690
        System.out.println(new Q17677().solution(str1, str2));

        str1 = "E=M*C^2";
        str2 = "e=m*c^2";    //65536
        System.out.println(new Q17677().solution(str1, str2));
    }
}
