package boj.study;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 미로 탐색
 * (BFS) - 거리 측정
 */
public class BOJ2178 {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, -0, 1, -1};

    public int solution(int n, int m, int[][] board) {
        int[][] dist = new int[n][m];
        for (int[] d : dist) {
            Arrays.fill(d, -1);
        }
        return bfs(n, m, board, dist);
    }

    private int bfs(int n, int m, int[][] board, int[][] dist) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, 0));
        dist[0][0] = 1;

        while (!q.isEmpty()) {
            Pair current = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (board[nx][ny] == 0 || dist[nx][ny] != -1) continue;
                dist[nx][ny] = dist[current.x][current.y] + 1;
                q.offer(new Pair(nx, ny));
                if (nx == n - 1 && ny == m - 1) return dist[nx][ny];
            }
        }
        return 0;
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
