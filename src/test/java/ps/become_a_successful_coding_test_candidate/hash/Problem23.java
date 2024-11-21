package ps.become_a_successful_coding_test_candidate.hash;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 23 신고 결과 받기
- 한 유저를 여러 번 신고할 수도 있지만, 동일한 유저에 대한 신고 횟수는 1회로 처리됩니다.

풀이1 (Map<String, List<String>> 유저별 | 신고한 유저)
- 유저별 신고 당한 횟수를 구한다 Map<String, Integer>
-- 중복 신고 제거 Set 사용
- 유저별 | 신고한 유저 List Map 을 만든다 Map<String, List<String>>
- id_list 로 유저별 신고한 유저 List Map loop 돌면서 결과를 만든다
-- 신고한 유저 List 에서 유저별 신고 당한 횟수가 k 가 넘으면 +1

풀이2 (Map<String, Set<String>> 신고당한 유저 | 신고한 유저)
- 신고당한 유저별 | 신고한 유저 List Map 을 만든다 Map<String, Set<String>>
-- Set 을 쓰므로, 중복 신고는 무시된다
- 신고당한 유저별 | 신고한 유저 List Map 을 loop
 */
public class Problem23 {

    static class Solution1 {
        public int[] solution(String[] id_list, String[] report, int k) {
            Map<String, List<String>> userReportMap = new HashMap<>();
            Map<String, Integer> reportCountMap = new HashMap<>();
            Set<String> duplicateCheck = new HashSet<>();
            for (String r : report) {
                String[] split = r.split(" ");
                String from = split[0];
                String to = split[1];

                if (!duplicateCheck.contains(r)) {
                    duplicateCheck.add(r);
                    reportCountMap.put(to, reportCountMap.getOrDefault(to, 0) + 1);
                    userReportMap.computeIfAbsent(from, key -> new ArrayList<>()).add(to);
                }
            }

//            List<Integer> result = new ArrayList<>();
//            for (String id : id_list) {
//                List<String> reportedUsers = userReportMap.getOrDefault(id, new ArrayList<>());
//
//                int count = 0;
//                for (String reportedUser : reportedUsers) {
//                    if (reportCountMap.get(reportedUser) >= k) {
//                        count++;
//                    }
//                }
//                result.add(count);
//            }
//
//            return result.stream().mapToInt(Integer::intValue).toArray();

            return Arrays.stream(id_list)
                    .mapToInt(id -> (int) userReportMap.getOrDefault(id, new ArrayList<>()).stream()
                            .filter(reportedUser -> reportCountMap.getOrDefault(reportedUser, 0) >= k)
                            .count()
                    )
                    .toArray();
        }
    }

    static class Solution2 {
        public int[] solution(String[] id_list, String[] report, int k) {
            Map<String, Set<String>> reportedUserMap = new HashMap<>();
            for (String r : report) {
                String[] split = r.split(" ");
                String from = split[0];
                String to = split[1];
                reportedUserMap.computeIfAbsent(to, key -> new HashSet<>()).add(from);
            }

            Map<String, Integer> countmap = new HashMap<>();
            for (Map.Entry<String, Set<String>> entry : reportedUserMap.entrySet()) {
                if (entry.getValue().size() >= k) {
                    entry.getValue().forEach(it -> countmap.put(it, countmap.getOrDefault(it, 0) + 1));
                }
            }

            int[] result = new int[id_list.length];
            for (int i = 0; i < id_list.length; i++) {
                result[i] = countmap.getOrDefault(id_list[i], 0);
            }

            return result;
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"muzi", "frodo", "apeach", "neo"},
                        new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"},
                        2,
                        new int[]{2, 1, 1, 0}
                ),
                Arguments.of(
                        new String[]{"con", "ryan"},
                        new String[]{"ryan con", "ryan con", "ryan con", "ryan con"},
                        3,
                        new int[]{0, 0}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] id_list, String[] report, int k, int[] expected) {
        assertThat(new Problem23.Solution1().solution(id_list, report, k)).isEqualTo(expected);
        assertThat(new Problem23.Solution2().solution(id_list, report, k)).isEqualTo(expected);
    }
}
