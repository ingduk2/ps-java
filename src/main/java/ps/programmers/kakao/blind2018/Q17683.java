package ps.programmers.kakao.blind2018;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * [3차] 방금그곡
 * https://programmers.co.kr/learn/courses/30/lessons/17683
 *
 * - 음악정보 parse
 * - 시간만큼 재생되는멜로디 구하기
 * - 입력 멜로디와 비교. 후 리스트에 추가.
 * - 리스트 정렬
 *
 * error
 * - C, C# 구분하기 위해 List 사용.
 * - 정렬 조건 확인.
 * - 멜로디 List 비교시에 totalPlayMusic.size() - mList.size() + 1; +1 없어서 마지막부분 체크 안됨..
 *
 * 참고
 * - #붙은 글자를 아예 다른 문자로 치환하는 방법도 있음.
 * - String 포함 비교시 indexOf
 */
public class Q17683 {

    private static class Music {
        private String title;
        private int playTime;
        private int order;

        public Music(String title, int playTime, int order) {
            this.title = title;
            this.playTime = playTime;
            this.order = order;
        }

        public String getTitle() {
            return title;
        }

        public int getPlayTime() {
            return playTime;
        }

        public int getOrder() {
            return order;
        }

        @Override
        public String toString() {
            return "Music{" +
                    "title='" + title + '\'' +
                    ", playTime=" + playTime +
                    ", order=" + order +
                    '}';
        }
    }



    public String solution(String m, String[] musicinfos) {
        List<String> mList = getMelodies(m);

        List<Music> answerMusics = new ArrayList<>();

        //music parse
        for (int i = 0; i < musicinfos.length; i ++) {
            String[] split = musicinfos[i].split(",");
            LocalTime startTime = LocalTime.parse(split[0]);
            LocalTime endTime = LocalTime.parse(split[1]);
            String title = split[2];
            String melody = split[3];

            List<String> totalPlayMusic = getTotalPlayMusic(startTime, endTime, melody);
            if (compareMusic(totalPlayMusic, mList)) {
                answerMusics.add(new Music(title, totalPlayMusic.size(), i));
            }
        }

        if (answerMusics.size() == 0) {
            return "(None)";
        } else {
            answerMusics.sort((o1, o2) -> {
                if (o1.getPlayTime() == o2.getPlayTime()) {
                    return o1.getOrder() - o2.getOrder();
                }
                return Integer.compare(o2.getPlayTime(), o1.getPlayTime());
            });
        }

        return answerMusics.get(0).getTitle();
    }

    private boolean compareMusic(List<String> totalPlayMusic, List<String> mList) {
        if (totalPlayMusic.size() < mList.size()) {
            return false;
        }

        System.out.println(totalPlayMusic);
        System.out.println(mList);

        int totalEqualCnt = mList.size();
        for (int i = 0; i < totalPlayMusic.size() - mList.size() + 1; i++) {
            int equalCnt = 0;
            for (int j = 0; j < mList.size(); j++) {
                System.out.println(totalPlayMusic.get(i + j) + "  " + mList.get(j));
                if (totalPlayMusic.get(i + j).equals(mList.get(j))) {
                    equalCnt++;
                } else {
                    break;
                }

                if (equalCnt == totalEqualCnt) return true;
            }
        }
        return false;
    }

    private List<String> getTotalPlayMusic(LocalTime startTime, LocalTime endTime, String melody) {

        List<String> totalPlayList = new ArrayList<>();

        int playTime = Math.toIntExact(startTime.until(endTime, ChronoUnit.MINUTES));

        List<String> melodyList = getMelodies(melody);
        int musicLen = melodyList.size();

        if (playTime > musicLen) {
            int checkTime = 0;
            for (int j = 0; j < playTime; j+=musicLen) {
                checkTime += musicLen;
                if (checkTime > playTime) {
                    checkTime -= musicLen;
                    break;
                }
                totalPlayList.addAll(melodyList);
            }
            totalPlayList.addAll(melodyList.subList(0, playTime - checkTime));
        } else {
            totalPlayList.addAll(melodyList.subList(0, playTime));
        }
        return totalPlayList;
    }

    private List<String> getMelodies(String m) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < m.length() - 1; i++) {
            char curC = m.charAt(i);
            char nextC = m.charAt(i + 1);
            if (nextC == '#') {
                result.add(String.valueOf(curC) + String.valueOf(nextC));
                i++;
            } else {
                result.add(String.valueOf(curC));
            }
        }

        char lastC = m.charAt(m.length() - 1);
        if (lastC != '#') {
            result.add(String.valueOf(lastC));
        }

        return result;
    }

    public static void main(String[] args) {
        String m = "ABCDEFG";
        String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}; //	"HELLO"
        String answer = "HELLO";
        System.out.println(new Q17683().solution(m, musicinfos) + " answer : " + answer);

        m = "CC#BCC#BCC#BCC#B";
        musicinfos = new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}; //	"FOO"
        answer = "FOO";
        System.out.println(new Q17683().solution(m, musicinfos) + " answer : " + answer);

        m = "ABC";
        musicinfos = new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}; //	"WORLD"
        answer = "WORLD";
        System.out.println(new Q17683().solution(m, musicinfos) + " answer : " + answer);

    }
}
