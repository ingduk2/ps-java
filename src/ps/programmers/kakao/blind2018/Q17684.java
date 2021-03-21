package ps.programmers.kakao.blind2018;


import java.util.*;
import java.util.stream.Collectors;

/**
 * [3차] 압축
 * https://programmers.co.kr/learn/courses/30/lessons/17684
 */
public class Q17684 {

    private Map<String, Integer> dict = new HashMap<>();

    public int[] solution(String msg) {
//        1 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
//        2 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
//        3 w에 해당하는  사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
//        4 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.
//        5 단계 2로 돌아간다.

        List<Integer> answer = new ArrayList<>();

        //init dict
        int maxDict = 26;
        char a = 'A';
        for (int i = 0; i < maxDict; i++) {
            dict.put(String.valueOf((char) (a + i)), i + 1);
        }

        //convert msg to Queue
        Queue<String> msgQueue = new LinkedList<>();
        Collections.addAll(msgQueue, msg.split(""));

        while (!msgQueue.isEmpty()) {
            String w = getMaxStr(msgQueue);
            answer.add(dict.get(w));

            for (int i = 0; i < w.length(); i++) {
                msgQueue.poll();
            }

            dict.put(w + msgQueue.peek(), ++maxDict);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    private String getMaxStr(Queue<String> msgQueue) {
        String maxStr = "";
        String str = "";
        for (String s : msgQueue) {
            str += s;
            if (dict.containsKey(str)) {
                maxStr = str;
            } else {
                break;
            }
        }
        return maxStr;
    }

    public static void main(String[] args) {
        String msg = "KAKAO";//	[11, 1, 27, 15]
        System.out.println(Arrays.toString(new Q17684().solution(msg)));

        msg = "TOBEORNOTTOBEORTOBEORNOT";//	[20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34]
        System.out.println(Arrays.toString(new Q17684().solution(msg)));

        msg = "ABABABABABABABAB"; //	[1, 2, 27, 29, 28, 31, 30]
        System.out.println(Arrays.toString(new Q17684().solution(msg)));
    }
}
