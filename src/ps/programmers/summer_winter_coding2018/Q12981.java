package ps.programmers.summer_winter_coding2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 영어 끝말잇기
 * https://programmers.co.kr/learn/courses/30/lessons/12981
 * 중복비교 string contains, set size, hashmap null check 로도 가능함.
 */
public class Q12981 {
    public int[] solution(int n, String[] words) {

        int failNumber = 0;
        int failOrder = 0;

        List<String> duplicateList = new ArrayList<>();

        String prevEndStr = words[0].substring(0, 1);
        for (int i = 0; i < words.length; i++) {
            int num = i % n;
            String word = words[i];
            String currentStartStr = word.substring(0, 1);

            //check prevEnd , currentStart || 중복 check
            if (!prevEndStr.equals(currentStartStr) || duplicateList.contains(word)) {
                failNumber = num + 1;
                failOrder = (i / n) + 1;
                break;
            }

            prevEndStr = word.substring(word.length() - 1);

            duplicateList.add(word);
        }

        int[] answer = new int[2];
        answer[0] = failNumber;
        answer[1] = failOrder;
        return answer;
    }

    public static void main(String[] args) {
        int n = 3;
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"	};
        System.out.println(Arrays.toString(new Q12981().solution(n, words)));

        n = 5;
        words = new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        System.out.println(Arrays.toString(new Q12981().solution(n, words)));

        n = 2;
        words = new String[]{"hello", "one", "even", "never", "now", "world", "draw"	};
        System.out.println(Arrays.toString(new Q12981().solution(n, words)));
    }
}
