package ps.thiscodingtest.graphtheory;

import java.util.Scanner;

/**
 * 기본적인 서로소 집합 알고리즘
 */
public class Ex10_1_DisjointSet {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        int[] parent = new int[v + 1];

        //부모 테이블상에서, 부모를 자기 자신으로 초기화
        for (int i = 1; i < v + 1; i++) {
            parent[i] = i;
        }

        //union 수행
        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            unionParent(parent, a, b);
        }

        //출력
        System.out.print("각 원소가 속한 집합 출력 : ");
        for (int i = 1; i < v + 1; i++) {
            System.out.print(findParent(parent, i)+ " ");
        }

        System.out.println();

        System.out.print("부모 테이블 : ");
        for (int i = 1; i < v + 1; i++) {
            System.out.print(parent[i] + " ");
        }

    }

    private static void unionParent(int[] parent, int a, int b) {
        a = findParent(parent, a);
        b = findParent(parent, b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }


    }

    private static int findParent(int[] parent, int x) {
        //루트 노드가 아니라면, 루트 노드를 찾을 떄까지 재귀 호출
        if (parent[x] != x) {
            return findParent(parent, parent[x]);
        }
        return x;
    }
}
