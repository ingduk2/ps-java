package ps.programmers.summer_winter_coding2018;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 스킬트리
 * https://programmers.co.kr/learn/courses/30/lessons/49993
 */
public class Q49993 {

    private static class Tuple {
        private String str;
        private int order;

        public Tuple(String str, int order) {
            this.str = str;
            this.order = order;
        }

        public String getStr() {
            return str;
        }

        public int getOrder() {
            return order;
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "str='" + str + '\'' +
                    ", order=" + order +
                    '}';
        }
    }

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (int i = 0; i < skill_trees.length; i++) {
            List<Tuple> skillList = new ArrayList<>();
            String skill_tree = skill_trees[i];

            for (int j = 0; j < skill.length(); j++) {
                char s = skill.charAt(j);
                for (int k = 0; k < skill_tree.length(); k++) {
                    char st = skill_tree.charAt(k);
                    if (s == st) skillList.add(new Tuple(String.valueOf(st), k));
                }
            }

            skillList.sort(Comparator.comparingInt(Tuple::getOrder));
            String skillListStr = skillList.stream().map(Tuple::getStr).collect(Collectors.joining(""));

            if (skill.startsWith(skillListStr)) {
                answer += 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        System.out.println(new Q49993().solution(skill, skill_trees));
    }
}
