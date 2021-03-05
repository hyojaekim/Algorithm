package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class BOJ1600 {

	@Test
	void name() {
		int solution = bfs(1, 4, 4, new int[][]{
						{0, 0, 0, 0},
						{1, 0, 0, 0},
						{0, 0, 1, 0},
						{0, 1, 0, 0}
		});

		Assertions.assertEquals(solution, 4);
	}

	@Test
	void test2() {
		int solution = bfs(1, 4, 4, new int[][]{
						{0, 1, 1, 1},
						{0, 0, 1, 1},
						{1, 0, 1, 1},
						{1, 1, 1, 0},
		});

		Assertions.assertEquals(solution, 4);
	}

	int[] dx = {0, -1, 0, 1};
	int[] dy = {1, 0, -1, 0};
	int[] ndx = {1, 2, 2, 1, -1, -2, -2, -1};
	int[] ndy = {2, 1, -1, -2, -2, -1, 1, 2};

	public int bfs(int k, int w, int h, int[][] board) {
		boolean[][][] visited = new boolean[h][w][k + 1]; //(x, y) -> 말 이동 횟수 만큼 방문했는가?
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0, k, 0));
		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (cur.x == h - 1 && cur.y == w - 1) return cur.cnt;

			for (int i = 0; i < 4; i++) { //상하좌우 이동
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= h || ny >= w || board[nx][ny] == 1 || visited[nx][ny][cur.k]) continue;
				visited[nx][ny][cur.k] = true;
				q.offer(new Point(nx, ny, cur.k, cur.cnt + 1)); //말 이동 횟수 변동 x, 이동 횟수 + 1
			}

			if (cur.k <= 0) continue;

			for (int i = 0; i < 8; i++) {
				int nx = cur.x + ndx[i];
				int ny = cur.y + ndy[i];

				if (nx < 0 || ny < 0 || nx >= h || ny >= w || board[nx][ny] == 1 || visited[nx][ny][cur.k]) continue;
				visited[nx][ny][cur.k - 1] = true;
				q.offer(new Point(nx, ny, cur.k - 1, cur.cnt + 1)); //말 이동 횟수 -1, 이동 횟수 + 1
			}
		}
		return -1;
	}

	static class Point {
		int x, y, k, cnt;

		public Point(int x, int y, int k, int cnt) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.cnt = cnt;
		}
	}
}
