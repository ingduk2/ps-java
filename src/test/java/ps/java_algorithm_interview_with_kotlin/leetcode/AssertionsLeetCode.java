package ps.java_algorithm_interview_with_kotlin.leetcode;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertionsLeetCode {
    private AssertionsLeetCode() {}

    public static void assertListNode(ListNode result, ListNode expected) {
        List<Integer> resultList = new ArrayList<>();
        List<Integer> expectedList = new ArrayList<>();

        while (result != null) {
            resultList.add(result.val);
            result = result.next;
        }

        while (expected != null) {
            expectedList.add(expected.val);
            expected = expected.next;
        }

        assertThat(resultList).isEqualTo(expectedList);
    }

    public static void assertListNode(ListNode result, int[] expected) {
        for (int e : expected) {
            assertThat(result.val).isEqualTo(e);
            result = result.next;
        }
    }
}
