package ps.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p1260 {
    private static List<Integer>[] nodes;
    private static boolean[] checked;

    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        checked[start] = true;
        while(!q.isEmpty()){
            int x = q.remove();
            System.out.print(x + " ");
            for(int y : nodes[x]){
                if(!checked[y]){
                    checked[y] = true;
                    q.add(y);
                }
            }
        }
    }

    public static void dfs(int start){
        if(checked[start]) return;
        checked[start] = true;
        System.out.print(start + " ");
        for(int y : nodes[start]){
            if(!checked[y]) dfs(y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int start = Integer.parseInt(line[2]);

        checked = new boolean[n+1];
        nodes = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            nodes[i] = new ArrayList<>();
        }

        for(int i=1; i<=m; i++){
            String[] edge = br.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            nodes[u].add(v);
            nodes[v].add(u);
        }

        for (List<Integer> node : nodes){
            Collections.sort(node);
        }

        dfs(start);
        checked = new boolean[n+1];
        System.out.println();
        bfs(start);


    }
}
