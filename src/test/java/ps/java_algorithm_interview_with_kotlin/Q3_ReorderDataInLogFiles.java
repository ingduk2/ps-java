package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/reorder-data-in-log-files/
 * - letter-logs : identifier 제외하고 lowercase
 * - digit-logs  : identifier 제외하고 숫자
 * 1. letter-logs -> digit-logs
 * 2. letter-logs 사전적으로 정렬, 내용이 같다면 identifiers 로 사전순 정렬
 * 3. digit-logs 는 상대적인 순서를 유지 (입력 순서)
 *
 * - letter-logs, digit-logs 를 분리
 * - letter-logs 를 조건 대로 정렬 한 후에, digit-logs 를 붙이자
 * - 전부다 정렬 시 속도가 느림
 */
public class Q3_ReorderDataInLogFiles {

    static class Log {
        private final String fullLog;
        private final String identifier;
        private final String content;
        private final Type type;

        enum Type {
            LETTER, DIGIT
        }

        public Log(String log) {
            this.fullLog = log;

            String[] splits = log.split(" ", 2);
            this.identifier = splits[0];
            this.content = splits[1];

            if (Character.isAlphabetic(content.charAt(0))) {
                this.type = Type.LETTER;
            } else {
                this.type = Type.DIGIT;
            }
        }

        public String getFullLog() {
            return fullLog;
        }

        public String getIdentifier() {
            return identifier;
        }

        public String getContent() {
            return content;
        }

        public Type getType() {
            return type;
        }
    }

    public String[] reorderLogFiles(String[] logs) {
        List<Log> logList = Arrays.stream(logs)
                .map(Log::new)
                .toList();
        List<Log> letterLogList = logList.stream()
                .filter(it -> it.getType() == Log.Type.LETTER)
                .sorted(Comparator
                        .comparing(Log::getContent)
                        .thenComparing(Log::getIdentifier))
                .toList();
        List<Log> digitLogList = logList.stream()
                .filter(it -> it.getType() == Log.Type.DIGIT)
                .toList();

        return Stream.concat(letterLogList.stream(), digitLogList.stream())
                .map(Log::getFullLog)
                .toList()
                .toArray(new String[0]);
    }


    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new String[]{"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"},
                        new String[]{"let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"}),
                Arguments.of(
                        new String[]{"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"},
                        new String[]{"g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"})
        );
    }
    @ParameterizedTest
    @MethodSource("arguments")
    void test(String[] input, String[] expected) {
        Q3_ReorderDataInLogFiles problem = new Q3_ReorderDataInLogFiles();
        String[] result = problem.reorderLogFiles(input);
        assertThat(result).containsExactly(expected);
    }
}
