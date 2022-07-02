package ps.programmers.kakao.blind2019;

import java.util.*;

/**
 * 오픈 채팅방
 * https://programmers.co.kr/learn/courses/30/lessons/42888
 */
public class Q42888 {

    private static class User {
        private String command;
        private String uid;

        public User(String command, String uid) {
            this.command = command;
            this.uid = uid;
        }

        public String getCommand() {
            return command;
        }

        public String getUid() {
            return uid;
        }
    }

    private List<User> userList;
    private Map<String, String> userMap;

    public String[] solution(String[] record) {
        List<String> answer = new ArrayList<>();

        userList = new ArrayList<>();
        userMap = new HashMap<>();

        for (String r : record) {
            String[] rArr = r.split(" ");
            String command = rArr[0];
            String uid = rArr[1];
            if (command.equals("Enter")) {
                userMap.put(uid, rArr[2]);
                userList.add(new User(command, uid));
            } else if (command.equals("Leave")){
                userList.add(new User(command, uid));
            } else if (command.equals("Change")) {
                userMap.put(uid, rArr[2]);
            }
        }

        for (User user : userList) {
            StringBuilder sb = new StringBuilder();
            sb.append(userMap.get(user.getUid())).append("님이 ").append(user.getCommand().equals("Enter") ? "들어왔습니다." : "나갔습니다.");
            answer.add(sb.toString());
        }

        return answer.toArray(new String[0]);
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                new Q42888().solution(
                        new String[]
                                {"Enter uid1234 Muzi",
                                        "Enter uid4567 Prodo",
                                        "Leave uid1234",
                                        "Enter uid1234 Prodo",
                                        "Change uid4567 Ryan"})));
    }
}
