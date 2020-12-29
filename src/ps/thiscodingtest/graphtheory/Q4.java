package ps.thiscodingtest.graphtheory;

import com.sun.xml.internal.fastinfoset.tools.XML_SAX_StAX_FI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 커리큘럼
 */
/*
5
10 -1
10 1 -1
4 1 -1
4 3 1 -1
3 3 -1
 */
public class Q4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        int[] indegree = new int[n + 1];
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] time = new int[n + 1];


        for (int i = 1; i < n + 1; i++) {
            String[] line = br.readLine().split(" ");
            time[i] = Integer.parseInt(line[0]);
            for (int j = 1; j < line.length -1; j++) {
                indegree[i] += 1;
                graph[Integer.parseInt(line[j])].add(i);
            }
        }

        //topology sort
        int[] result = deepcopyArr(time);
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i < n + 1; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            for (Integer i : graph[now]) {
                result[i] = Math.max(result[i], result[now] + time[i]);
                indegree[i] -= 1;
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            System.out.println(result[i]);
        }
    }

    private static int[] deepcopyArr(int[] time) {
        int[] copy = time.clone();
        return copy;
    }
}
