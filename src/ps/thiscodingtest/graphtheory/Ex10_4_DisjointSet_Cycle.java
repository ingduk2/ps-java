package ps.thiscodingtest.graphtheory;

import java.util.Scanner;

/**
 * 서로소 집합을 활용한 사이클 판별
 */
public class Ex10_4_DisjointSet_Cycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        int[] parent = new int[v + 1];

        for (int i = 1; i < v + 1; i++) {
            parent[i] = i;
        }

        boolean cycle = false;

        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (find_parent(parent, a) == find_parent(parent, b)) {
                cycle = true;
                break;
            } else {
                unionParent(parent, a, b);
            }
        }

        if (cycle) {
            System.out.println("사이클 O");
        } else {
            System.out.println("사이클 X");
        }
    }

    private static void unionParent(int[] parent, int a, int b) {
        a = find_parent(parent, a);
        b = find_parent(parent, b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private static int find_parent(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find_parent(parent, parent[x]);
        }
        return parent[x];
    }
}
