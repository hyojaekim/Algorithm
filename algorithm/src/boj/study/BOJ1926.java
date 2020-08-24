package boj.study;

import java.util.LinkedList;
import java.util.Queue;

public class BOJ1926 {

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    int[][] board;
    boolean[][] visited;

    public int[] solution(int n, int m, int[][] board) {
        int numberOfArea = 0;
        int maxArea = 0;
        this.visited = new boolean[n][m];
        this.board = board;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    numberOfArea++;
                    maxArea = Math.max(maxArea, bfs(i, j, n, m));
                }
            }
        }
        int[] result = new int[2];
        result[0] = numberOfArea;
        result[1] = maxArea;
        return result;
    }

    private int bfs(int x, int y, int n, int m) {
        int cnt = 1;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(x, y));
        visited[x][y] = true;
        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (board[nx][ny] != 1 || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                cnt++;
                q.offer(new Pair(nx, ny));
            }
        }
        return cnt;
    }

    private static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
