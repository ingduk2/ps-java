package ps.thiscodingtest.previous_problems.sort;

import java.util.*;

/**
 * 카드 정렬하기
 * 처음에만 정렬하면 끝이 아님.
 * 묶음이 더 커지는 경우가 있기 때문에 계속 정렬을 하거나
 * 우선순위 큐를 사용해야 한다.
 * 4
 * 10
 * 10
 * 10
 * 10
 * 의 경우 처음 묶으면 20 10 10 이 되는데 여기서 10 10 을 먼저 묶어야함.
 */
public class Q26 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            priorityQueue.add(sc.nextInt());
        }


        int result = 0;

        while (priorityQueue.size() != 1) {
            int a = priorityQueue.poll();
            int b = priorityQueue.poll();

            int sum = a + b;
            result += sum;
            priorityQueue.add(sum);
        }

        System.out.println(result);
    }
}
