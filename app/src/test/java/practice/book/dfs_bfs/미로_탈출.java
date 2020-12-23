package practice.book.dfs_bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 미로_탈출 {

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    int[][] board;
    int[][] dist;
    public int solution(int n, int m, int[][] board) {
        int result = 0;
        this.board = board;
        this.dist = fill(n, m);

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));
        dist[0][0] = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (dist[nx][ny] != -1 || board[nx][ny] == 0) continue;
                q.offer(new Node(nx, ny));
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                if (nx == n - 1 && ny == m - 1) return dist[nx][ny];
            }
        }
        return result;
    }

    private int[][] fill(int n, int m) {
        int[][] result = new int[n][m];
        for (int i = 0; i < result.length; i++) {
            Arrays.fill(result[i], -1);
        }
        return result;
    }

    class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
