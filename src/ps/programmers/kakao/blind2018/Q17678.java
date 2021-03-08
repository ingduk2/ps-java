package ps.programmers.kakao.blind2018;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [1차] 셔틀버스
 * https://programmers.co.kr/learn/courses/30/lessons/17678
 *
 * 마지막에 타고싶어하므로
 * 1. 시간 정렬.
 * 2. 차례대로 탑승
 * 3. 마지막 버스에서 탑승한 인원 < m 이면 탈수있기 때문에 마지막시간에 탑승.
 * 4. 마지막에 탈수 없다면 맨 마지막 탑승한 사람보다 -1 분 먼저오면 된다.
 *
 * 런타임 에러 : for문 timetable 에서 break조건이 없을때 났는데
 *          왜 난거지....
 *          문제에서 24:00 이 들어온다고함..
 *          LocalTime.of(24 , 0)들어가면 익셉션
 */
public class Q17678 {

    private LocalTime getLocalTime(String timeStr) {
        String[] split = timeStr.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        return LocalTime.of(hour, minute);
    }

    public String solution(int n, int t, int m, String[] timetable) {

        Arrays.sort(timetable);
        //n회 t분 m명
        String startTime = "09:00";
        LocalTime startBusTime = getLocalTime(startTime);

        int idx = 0;
        int lastInCnt= 0;
        LocalTime lastCrewTime = getLocalTime(timetable[0]);
        for (int i = 0; i < n; i++) {
            LocalTime busTime = startBusTime.plusMinutes(t * i);
            int cnt = 0;
            int inCnt = 0;
            for (int j = idx; j < timetable.length; j++) {
                cnt++;
                LocalTime crewTime = getLocalTime(timetable[j]);
                if (crewTime.compareTo(busTime) < 1 && cnt <= m) {
                    idx = j + 1;
                    inCnt++;
                    lastCrewTime = crewTime;
                }

                if (inCnt == m) break;
                if (crewTime.compareTo(busTime) > 0) break;
                
            }
            lastInCnt = inCnt;
        }

        if (lastInCnt < m) {
            return startBusTime.plusMinutes(t * (n - 1)).toString();
        } else {
            return lastCrewTime.minusMinutes(1).toString();
        }

    }

    //시간이 00:00 ~ 23:59 이므로 분으로 바꿔도 순서대로임.
    public String solution2(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable);
        //n회 t분 m명
        String startTime = "09:00";
        LocalTime startBusTime = getLocalTime(startTime);

        Queue<LocalTime> queue = new LinkedList<>();
        for (int i = 0; i < timetable.length; i++) {
            queue.add(getLocalTime(timetable[i]));
        }

        int lastInCnt = 0;
        LocalTime lastInCrewTime = queue.peek();
        for (int i = 0; i < n; i++) {
            LocalTime busTime = startBusTime.plusMinutes(t * i);
            lastInCnt = 0;
            while (!queue.isEmpty() && lastInCnt < m) {
                LocalTime crewTime = queue.peek();
                if (crewTime.compareTo(busTime) > 0) break;

                if (crewTime.compareTo(busTime) < 1) {
                    lastInCnt++;
                    queue.poll();
                    lastInCrewTime = crewTime;
                }
            }
        }

        if (lastInCnt < m) {
            return startBusTime.plusMinutes(t * (n - 1)).toString();
        } else {
            return lastInCrewTime.minusMinutes(1).toString();
        }

    }



    public static void main(String[] args) {

        int n = 1;
        int t = 1;
        int m = 3;
        String[] timetable = {"08:00", "08:01", "08:02", "08:03", "08:00", "08:01", "08:02", "08:03", "08:00", "08:01", "08:02", "08:03"};//	"09:00"
        System.out.println(new Q17678().solution2(n, t, m, timetable) + " test answer " + "07:59");
        System.out.println();

        n = 1;
        t = 1;
        m = 5;
        timetable = new String[]{"08:00", "08:01", "08:02", "08:02", "08:02", "08:03"};//	"09:00"
        System.out.println(new Q17678().solution2(n, t, m, timetable) + " test answer " + "08:01");
        System.out.println();

        n = 3;
        t = 1;
        m = 5;
        timetable = new String[]{"08:00", "08:01", "08:02", "08:02", "08:02", "08:03"};//	"09:00"
        System.out.println(new Q17678().solution2(n, t, m, timetable) + " test answer " + "09:02");
        System.out.println();

//
        n = 2;
        t = 10;
        m = 2;
        timetable = new String[]{"09:10", "09:09", "08:00"};	//"09:09"
        System.out.println(new Q17678().solution2(n, t, m, timetable) + " answer " + "09:09");
        System.out.println();

        n = 2;
        t = 1;
        m = 2;
        timetable = new String[]{"09:00", "09:00", "09:00", "09:00"};	//"08:59"
        System.out.println(new Q17678().solution2(n, t, m, timetable) + " answer " + "08:59");
        System.out.println();

        n = 1;
        t = 1;
        m = 5;
        timetable = new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"};//	"00:00"
        System.out.println(new Q17678().solution2(n, t, m, timetable) + " answer " + "00:00");
        System.out.println();

        n = 1;
        t = 1;
        m = 1;
        timetable = new String[]{"23:59"}; //	"09:00"
        System.out.println(new Q17678().solution2(n, t, m, timetable) + " answer " + "09:00");
        System.out.println();

        n = 10;
        t = 60;
        m = 45;
        timetable = new String[]{"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}; //	"18:00"
        System.out.println(new Q17678().solution2(n, t, m, timetable) + " answer " + "18:00");
        System.out.println();


    }
}
