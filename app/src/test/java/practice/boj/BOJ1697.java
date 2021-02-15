package practice.boj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 숨바꼭질
 * (BFS) - 1차원 배열
 */
public class BOJ1697 {

    public int solution(int n, int k) {
        int[] dist = new int[100002];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        dist[n] = 0;

        while (!q.isEmpty()) {
            Integer cur = q.poll();
            for (int temp : new int[]{cur - 1, cur + 1, cur * 2}) {
                if (temp < 0 || temp >= dist.length) continue;
                if (dist[temp] >= 0) continue;
                q.offer(temp);
                dist[temp] = dist[cur] + 1;
            }
        }
        return dist[k];
    }
}
