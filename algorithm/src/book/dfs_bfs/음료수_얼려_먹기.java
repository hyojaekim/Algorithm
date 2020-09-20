package book.dfs_bfs;

import java.util.LinkedList;
import java.util.Queue;

public class 음료수_얼려_먹기 {

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    boolean[][] visited;
    int[][] board;
    int n;
    int m;

    public int solution(int n, int m, int[][] board) {
        int count = 0;
        this.n = n;
        this.m = m;
        this.visited = new boolean[n][m];
        this.board = board;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || board[i][j] == 1) continue;
                count++;
                dfs(i, j);
            }
        }
        return count;
    }

    public void dfs(int startX, int startJ) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(startX, startJ));
        visited[startX][startJ] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visited[nx][ny] || board[nx][ny] == 0) continue;
                q.offer(new Node(nx, ny));
            }
        }
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
