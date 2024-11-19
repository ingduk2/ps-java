package ps.become_a_successful_coding_test_candidate.hash;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 21 오픈 채팅방
모든 기록이 처리된 후, 최종적으로 방을 개설한 사람이 보게 되는 메시지

풀이
- 채팅방 메세지를 id 의 Enter, Leave 를 순서대로 저장
- user 의 Map<uid, nickname> 을 저장
-- Enter, Change 시 변경 시켜 준다
- 채팅방 메세지를 userMap 에서 찾아서 nickName + Enter, Leave 로 만든다
 */
public class Problem21 {

    static class Solution1 {
        public String[] solution(String[] record) {
            Map<String, String> userMap = new HashMap<>();
            Map<String, String> messageMap = Map.of(
                    "Enter", "님이 들어왔습니다.",
                    "Leave", "님이 나갔습니다."
            );
            List<String[]> chatMassages = new ArrayList<>();

            for (String s : record) {
                String[] cmd = s.split(" ");
                String command = cmd[0];
                String uid = cmd[1];

                switch (command) {
                    case "Enter" -> {
                        userMap.put(uid, cmd[2]);
                        chatMassages.add(new String[]{uid, messageMap.get(command)});
                    }
                    case "Change" -> {
                        userMap.put(uid, cmd[2]);
                    }
                    case "Leave" -> {
                        chatMassages.add(new String[]{uid, messageMap.get(command)});
                    }
                }
            }

            return chatMassages.stream()
                    .map(it -> userMap.get(it[0]) + it[1])
                    .toArray(String[]::new);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"},
                        new String[]{"Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] record, String[] expected) {
        assertThat(new Solution1().solution(record)).isEqualTo(expected);
    }
}
