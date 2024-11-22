package ps.become_a_successful_coding_test_candidate.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/*
문제 25 트리 순회 (저자 출제)
- 전위 순회, 중외 순회, 후위 순회 결과를 반환

 */
public class Problem25 {

    public String[] solution(int[] nodes) {
        String[] result = new String[3];
        result[0] = preorder(nodes, 0).trim();
        result[1] = inorder(nodes, 0).trim();
        result[2] = postorder(nodes, 0).trim();

        return result;
    }

    private String preorder(int[] nodes, int idx) {
        if (idx >= nodes.length) {
            return "";
        }

        return nodes[idx] + " " +
                preorder(nodes, 2 * idx + 1) +
                preorder(nodes, 2 * idx + 2);
    }

    private String inorder(int[] nodes, int idx) {
        if (idx >= nodes.length) {
            return "";
        }

        return inorder(nodes, 2 * idx + 1) +
                nodes[idx] + " " +
                inorder(nodes, 2 * idx + 2);
    }

    private String postorder(int[] nodes, int idx) {
        if (idx >= nodes.length) {
            return "";
        }

        return postorder(nodes, 2 * idx + 1) +
                postorder(nodes, 2 * idx + 2) +
                nodes[idx] + " ";
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 2, 3, 4, 5, 6, 7},
                        new String[]{
                                "1 2 4 5 3 6 7",
                                "4 2 5 1 6 3 7",
                                "4 5 2 6 7 3 1",
                        }
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(int[] nodes, String[] expected) {
        assertThat(solution(nodes)).isEqualTo(expected);
    }
}
