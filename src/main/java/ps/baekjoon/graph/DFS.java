package ps.baekjoon.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DFS {
    private static List<Integer>[] a;
    private static boolean[] c;

    public static void dfs(int x){
        if(c[x]) return;
        c[x] = true;
        System.out.println(x + " ");
        for( int y : a[x]){
            if( c[y] == false) dfs(y);
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
        dfs(start);
        System.out.println();
    }
}
