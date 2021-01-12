package ps.programmers.kakao.winter_intern_2019;


import java.util.*;
import java.util.stream.Collectors;

/**
 * 튜플
 * https://programmers.co.kr/learn/courses/30/lessons/64065
 */
public class Q64065 {

    public int[] solution(String s) {
        int[] answer = {};

        List<List<Integer>> arrList = new ArrayList<>();

        //{ ... } remove {2},{2,1},{2,1,3},{2,1,3,4}
        s = s.substring(1, s.length()-1);
        // {2},{2,1},{2,1,3},{2,1,3,4},
        s += ",";
        // }, split [{2, {2,1, {2,1,3, {2,1,3,4]
        String[] parseArr = s.split("},");

        //remove { and split ,
        for (String parse : parseArr) {
            String[] split = parse.substring(1).split(","); //remove { and split ,
            List<Integer> collect = Arrays.stream(split).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
            arrList.add(collect);
        }

        // inner list sort by size
//        arrList.sort((List<Integer> o1, List<Integer> o2) -> o1.size() - o2.size());
        arrList.sort(Comparator.comparingInt(List::size));

        // 0 번째 삭제, 그 num을 나머지 list에서 제거, 반복
        List<Integer> result = new ArrayList<>();
        while (!arrList.isEmpty()) {
            List<Integer> remove = arrList.remove(0);
            int num = remove.get(0);
            result.add(num);

            for (List<Integer> inner : arrList) {
                for (Iterator<Integer> iter = inner.iterator(); iter.hasNext(); ) {
                    int innerNum = iter.next();
                    if (num == innerNum) {
                        iter.remove();
                        break;
                    }
                }
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Q64065().solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")));
        System.out.println(Arrays.toString(new Q64065().solution("{{1,2,3},{2,1},{1,2,4,3},{2}}")));
        System.out.println(Arrays.toString(new Q64065().solution("{{20,111},{111}}"	)));
        System.out.println(Arrays.toString(new Q64065().solution("{{123}}"	)));
        System.out.println(Arrays.toString(new Q64065().solution("{{4,2,3},{3},{2,3,4,1},{2,3}}")));
    }
}
