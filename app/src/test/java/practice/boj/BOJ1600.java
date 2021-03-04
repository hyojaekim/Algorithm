package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1600 {

	@Test
	void name() {
		int solution = solution(1, 4, 4, new int[][]{
						{0, 0, 0, 0},
						{1, 0, 0, 0},
						{0, 0, 1, 0},
						{0, 1, 0, 0}
		});

		Assertions.assertEquals(solution, 4);
	}

	int[] ndx = {-2, 2, 0, 0};
	int[] ndy = {0, 0, -2, 2};
	int[] ndxx = {0, 0, -1, 1};
	int[] ndyy = {-1, 1, 0, 0};

	int[] dx = {0, -1, 0, 1};
	int[] dy = {1, 0, -1, 0};

	public int solution(int k, int w, int h, int[][] board) {
		int[][] nightBoard = init(w, h);
		int[][] dist = init(w, h);

		return bfs(k, board, dist, nightBoard, w, h);
	}

	private int bfs(int k, int[][] board, int[][] dist, int[][] nightBoard, int w, int h) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));
		dist[0][0] = 0;
		nightBoard[0][0] = 0;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (cur.x == w - 1 && cur.y == h - 1) break;

			//나이트 4방향
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + ndx[i] + ndxx[i];
				int ny = cur.y + ndy[i] + ndyy[i];

				if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
				if (dist[nx][ny] != -1 || nightBoard[cur.x][cur.y] == k) continue; //k만큼 썼으면 pass
				dist[nx][ny] = dist[cur.x][cur.y] + 1;
				nightBoard[nx][ny] = nightBoard[cur.x][cur.y] + 1;
				q.offer(new Point(nx, ny));
			}

			//일반 4방향
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
				if (dist[nx][ny] != -1 || board[nx][ny] == 1) continue; //장애물도 확인
				dist[nx][ny] = dist[cur.x][cur.y] + 1;
				nightBoard[nx][ny] = nightBoard[cur.x][cur.y];
				q.offer(new Point(nx, ny));
			}
		}
		return dist[h - 1][w - 1];
	}

	private int[][] init(int w, int h) {
		int[][] result = new int[h][w];
		for (int i = 0; i < w; i++) {
			Arrays.fill(result[i], -1);
		}
		return result;
	}

	class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
