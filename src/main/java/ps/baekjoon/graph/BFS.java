package ps.baekjoon.graph;

import java.util.*;

public class BFS {
    static ArrayList<Integer>[] a;
    static boolean[] c;

    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        c[start] = true;
        while( !q.isEmpty()){
            int x = q.remove();
            System.out.println(x + " ");
            for ( int y : a[x]){
                if(c[y] == false){
                    c[y] = true;
                    q.add(y);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int start = sc.nextInt();
        a = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            a[i] = new ArrayList<>();
        }
        for(int i=1; i<=m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            a[u].add(v);
            a[v].add(u);
        }
        for(int i=1; i<=n; i++){
            Collections.sort(a[i]);
        }

        for(int j=1; j<=n; j++){
            System.out.println("== [" + j + "] :" + a[j]);
        }

        c = new boolean[n+1];
        bfs(start);
        System.out.println();
    }
}
