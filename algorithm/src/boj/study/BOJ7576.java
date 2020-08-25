package boj.study;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 토마토
 * (BFS) - 시작점이 여러개
 */
public class BOJ7576 {
    int[][] box;
    int[][] dist;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    private int solution(int n, int m, int[][] box) {
        this.box = box;
        this.dist = new int[n][m];
        Queue<Pair> q = new LinkedList<>();
        boolean isAllRipen = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 1) q.offer(new Pair(i, j));
                else if (box[i][j] == 0) {
                    isAllRipen = false;
                    dist[i][j] = -1;
                }
            }
        }
        if (isAllRipen) return 0;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (dist[nx][ny] >= 0) continue;
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                q.offer(new Pair(nx, ny));
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dist[i][j] == -1) return -1;
                result = Math.max(result, dist[i][j]);
            }
        }
        return result;
    }

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
