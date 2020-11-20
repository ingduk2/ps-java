package ps.programmers.dfs_bfs;

import sun.lwawt.macosx.NSPrintInfo;

import java.util.Arrays;

/**
 * 타겟 넘버
 */
public class Q43165 {

    public int dfs(int n, int[] arr, int target, boolean[] visit){
        int ret = 0;
        if(n == visit.length){
            int sum = 0;
            for(int i =0;i<visit.length;i++){
                if(visit[i]){
                    sum += arr[i];
                } else{
                    sum -= arr[i];
                }
            }
            if (target == sum) return 1;
            else return 0;
        }else{
            visit[n] = true;
            ret += dfs(n+1, arr, target, visit);
            visit[n] = false;
            ret += dfs(n+1, arr, target, visit);
        }
        return ret;
    }

    public int solution(int[] numbers, int target) {
        int answer = 0;
        boolean [] visit = new boolean[numbers.length];
        answer += dfs(0, numbers, target, visit);
        return answer;
    }


    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        System.out.println(new Q43165().solution(numbers, 3));

    }
}
