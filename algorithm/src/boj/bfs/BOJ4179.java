package boj.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 불!
 * (BFS) - 시작점이 두 종류
 */
public class BOJ4179 {
    int[][] distF;
    int[][] distJ;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    private String solution(int n, int m, char[][] board) {
        distF = new int[n][m];
        distJ = new int[n][m];
        Queue<Pair> fireQ = new LinkedList<>();
        Queue<Pair> jihoonQ = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            Arrays.fill(distF[i], -1);
            Arrays.fill(distJ[i], -1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'F') {
                    fireQ.offer(new Pair(i, j));
                    distF[i][j] = 0;
                } else if (board[i][j] == 'J') {
                    jihoonQ.offer(new Pair(i, j));
                    distJ[i][j] = 0;
                }
            }
        }

        while (!fireQ.isEmpty()) {
            Pair cur = fireQ.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (distF[nx][ny] >= 0 || board[nx][ny] == '#') continue;
                distF[nx][ny] = distF[cur.x][cur.y] + 1;
                fireQ.offer(new Pair(nx, ny));
            }
        }

        while (!jihoonQ.isEmpty()) {
            Pair cur = jihoonQ.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) return String.valueOf(distJ[cur.x][cur.y] + 1);
                if (distJ[nx][ny] >= 0 || board[nx][ny] == '#') continue;
                if (distF[nx][ny] != -1 && distF[nx][ny] <= distJ[cur.x][cur.y] + 1) continue;
                distJ[nx][ny] = distJ[cur.x][cur.y] + 1;
                jihoonQ.offer(new Pair(nx, ny));
            }
        }
        return "IMPOSSIBLE";
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
