package ps.programmers.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Q42627 {
    public static int solution(int[][] jobs) {

        int answer = 0;
        int end = 0; // 수행되고난 직후의 시간
        int jobsIdx = 0; // jobs 배열의 인덱스
        int count = 0; // 수행된 요청 갯수

        // 원본 배열 오름차순 정렬 (요청시간 오름차순)
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        // 처리 시간 오름차순으로 정렬되는 우선순위 큐(Heap)
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        // 요청이 모두 수행될 때까지 반복
        while (count < jobs.length) {
            System.out.println("===========");
            for (int[] ints : pq) {
                for (int anInt : ints) {
                    System.out.print(anInt+ " ");
                }
                System.out.println();
            }
            System.out.println("===========");

            // 하나의 작업이 완료되는 시점(end)까지 들어온 모든 요청을 큐에 넣음
            System.out.println("end "+ end);
            while (jobsIdx < jobs.length && jobs[jobsIdx][0] <= end) {
                System.out.println(jobs[jobsIdx][0] + "while ---" + jobs[jobsIdx][1]);
                pq.add(jobs[jobsIdx++]);
            }

            // 큐가 비어있다면 작업 완료(end) 이후에 다시 요청이 들어온다는 의미
            // (end를 요청의 가장 처음으로 맞춰줌)
            if (pq.isEmpty()) {
                System.out.println(jobs[jobsIdx][0] + "empty---" + jobs[jobsIdx][1]);
                end = jobs[jobsIdx][0];
                System.out.println("pq.isEmpty() " + end);
                // 작업이 끝나기 전(end 이전) 들어온 요청 중 가장 수행시간이 짧은 요청부터 수행
            } else {

                int[] temp = pq.poll();
                System.out.println("else " + temp[1] + "-" + temp[0]);
                answer += temp[1] + end - temp[0];
                end += temp[1];
                count++;
            }
        }

        return (int) Math.floor(answer / jobs.length);
    }

    public static void main(String[] args) {
        int[][] jobs = {{0,3}, {1,9}, {2,6}};
        System.out.println(solution(jobs));
    }
}
