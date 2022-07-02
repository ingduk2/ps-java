package ps.programmers.kakao.blind2018;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 캐시
 * https://programmers.co.kr/learn/courses/30/lessons/17680
 * LRU 캐시
 */
public class Q17680 {

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if (cacheSize == 0) {
            return cities.length * 5;
        }

        Deque<String> cache = new LinkedList<>();

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            if (cache.contains(city)) {
                cache.remove(city);
                cache.addFirst(city);
                answer += 1;
            } else {
                if (cache.size() == cacheSize) {
                    cache.removeLast();
                }
                cache.addFirst(city);
                answer += 5;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(new Q17680().solution(cacheSize, cities));

        cacheSize = 3;
        cities = new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
        System.out.println(new Q17680().solution(cacheSize, cities));

        cacheSize = 2;
        cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        System.out.println(new Q17680().solution(cacheSize, cities));

        cacheSize = 5;
        cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        System.out.println(new Q17680().solution(cacheSize, cities));

        cacheSize = 2;
        cities = new String[]{"Jeju", "Pangyo", "NewYork", "newyork" };
        System.out.println(new Q17680().solution(cacheSize, cities));

        cacheSize = 0;
        cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(new Q17680().solution(cacheSize, cities));
    }
}
