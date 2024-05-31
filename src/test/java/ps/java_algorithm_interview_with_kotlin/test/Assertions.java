package ps.java_algorithm_interview_with_kotlin.test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class Assertions {

    public static void assertList(List<List<Integer>> result, List<List<Integer>> expected) {
        List<List<Integer>> sortedExpected = expected.stream()
                .map(list -> list.stream().sorted().collect(Collectors.toList()))
                .sorted(Comparator.comparing(Object::toString))
                .toList();

        List<List<Integer>> sortedResult = result.stream()
                .map(list -> list.stream().sorted().collect(Collectors.toList()))
                .sorted(Comparator.comparing(Object::toString))
                .toList();

        assertThat(sortedResult).containsExactlyElementsOf(sortedExpected);
    }
}
