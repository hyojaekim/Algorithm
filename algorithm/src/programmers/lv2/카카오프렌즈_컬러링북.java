package programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;

public class 카카오프렌즈_컬러링북 {

    int size;
    int[] dx;
    int[] dy;
    int[][] picture;
    boolean[][] visited;
    Queue<Node> q;

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        init(m, n, picture);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canMove(i, j)) continue;
                numberOfArea++;
                bfs(this.picture[i][j], i, j, m, n);
                if (maxSizeOfOneArea < size) {
                    maxSizeOfOneArea = size;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private void init(int m, int n, int[][] picture) {
        this.dx = new int[]{-1, 1, 0, 0};
        this.dy = new int[]{0, 0, -1, 1};
        this.picture = picture;
        this.q = new LinkedList<>();
        this.visited = new boolean[m][n];
    }

    private void bfs(int color, int x, int y, int m, int n) {
        q.offer(new Node(x, y));
        this.visited[x][y] = true;
        this.size = 1;

        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (isBoundary(m, n, nx, ny) && canMove(nx, ny, color)) {
                    q.offer(new Node(nx, ny));
                    this.visited[nx][ny] = true;
                    this.size++;
                }
            }
        }
    }

    private boolean isBoundary(int m, int n, int nx, int ny) {
        return nx >= 0 && nx < m && ny >= 0 && ny < n;
    }

    private boolean canMove(int i, int j) {
        return this.picture[i][j] == 0 || this.visited[i][j];
    }

    private boolean canMove(int x, int y, int color) {
        return !this.visited[x][y] && this.picture[x][y] == color;
    }
}
