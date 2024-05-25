package ps.java_algorithm_interview_with_kotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/design-hashmap/description/
 * - hash 라이브러리 없이 HashMap 디자인
 *
 */
public class Q30_DesignHashMap {
    static class MyHashMap {
        static class Node {
            private int key;
            private int val;
            Node next;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        private final Node[] nodes = new Node[100000];

        public MyHashMap() {

        }

        public void put(int key, int value) {
            int index = key % nodes.length;
            // 해당 인덱스에 노드가 없으면 신규 생성 후 종
            if (nodes[index] == null) {
                nodes[index] = new Node(key, value);
                return;
            }

            // 인덱스에 노드가 존재한다면 연결 리스트로 처리
            Node node = nodes[index];
            while (node != null) {
                // 동일한 키가 있다면 값을 업데이트하고 종료
                if (node.key == key) {
                    node.val = value;
                    return;
                }
                // 다음 노드가 없다면 종료
                if (node.next == null) {
                    break;
                }

                node = node.next;
            }

            node.next = new Node(key, value);
        }

        public int get(int key) {
            int index = key % nodes.length;
            // 인덱스에 노드가 존재하지 않으면 -1
            if (nodes[index] == null) {
                return -1;
            }

            // 인덱스에 노드가 존재하면 일치하는 키 탐색
            Node node = nodes[index];
            while (node != null) {
                // 동일한 키가 있다면 값을 리턴
                if (node.key == key) {
                    return node.val;
                }

                node = node.next;
            }

            return -1;
        }

        public void remove(int key) {
            int index = key % nodes.length;
            // 해당 인덱스에 노드가 없다면 종료
            if (nodes[index] == null) {
                return;
            }

            // 첫 번째 노드 일 때의 삭제 처리
            Node node = nodes[index];
            if (node.key == key) {
                if (node.next == null) {
                    nodes[index] = null;
                } else {
                    nodes[index] = node.next;
                }
            }

            // 연결 리스트 노드 일 때의 삭제 처리
            Node prev = node;
            while (node != null) {
                if (node.key == key) {
                    prev.next = node.next;
                    return;
                }
                prev = node;
                node = node.next;
            }
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(
                        List.of("MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"),
                        List.of(List.of(), List.of(1, 1), List.of(2, 2), List.of(1), List.of(3), List.of(2, 1), List.of(2), List.of(2), List.of(2)),
                        new Object[]{null, null, null, 1, -1, null, 1, null, -1}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test(List<String> commands, List<List<Integer>> values, Object[] expected) {
        MyHashMap myHashMap = null;
        Object[] result = new Object[commands.size()];
        for (int i = 0; i < commands.size(); i++) {
            switch (commands.get(i)) {
                case "MyHashMap":
                    myHashMap = new MyHashMap();
                    result[i] = null;
                    break;
                case "put":
                    List<Integer> putVal = values.get(i);
                    myHashMap.put(putVal.get(0), putVal.get(1));
                    result[i] = null;
                    break;
                case "get":
                    List<Integer> getVal = values.get(i);
                    int get = myHashMap.get(getVal.get(0));
                    result[i] = get;
                    break;
                case "remove":
                    List<Integer> removeVal = values.get(i);
                    myHashMap.remove(removeVal.get(0));
                    result[i] = null;
                    break;
                default:
                    throw new RuntimeException();
            }
        }

        assertThat(result).isEqualTo(expected);
    }
}
