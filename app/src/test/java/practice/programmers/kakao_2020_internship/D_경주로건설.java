package practice.programmers.kakao_2020_internship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class D_경주로건설 {

	@Test
	void name() {
		int solution = solution(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
		Assertions.assertEquals(solution, 900);
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/67259
	 * [분류] BFS
	 *
	 * @param board 전체 맵 (0:비어있음, 1:벽)
	 * @return 직선도로 100원, 코너 500원으로 최소 비용으로 도착지점까지 건설하기
	 */
	int[] dx = {1, 0, -1, 0};
	int[] dy = {0, 1, 0, -1};

	public int solution(int[][] board) {
		int[][][] dist = initDist(board);
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0, 0));
		q.offer(new Point(0, 0, 1));
		dist[0][0][0] = 0;
		dist[0][0][1] = 0;

		return bfs(board, dist, q);
	}

	private int bfs(int[][] board, int[][][] dist, Queue<Point> q) {
		int max = board.length;
		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];

				if (nx < 0 || ny < 0 || nx >= max || ny >= max) continue;
				if (board[nx][ny] == 1) continue;

				int num = cur.dir == dir ? dist[cur.x][cur.y][cur.dir] + 100 : dist[cur.x][cur.y][cur.dir] + 600;
				if (dist[nx][ny][dir] == -1 || dist[nx][ny][dir] > num) {
					dist[nx][ny][dir] = num;
					q.offer(new Point(nx, ny, dir));
				}
			}
		}

		int result = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			if (dist[board.length - 1][board.length - 1][i] == -1) continue;
			result = Math.min(result, dist[board.length - 1][board.length - 1][i]);
		}
		return result;
	}

	private int[][][] initDist(int[][] board) {
		int[][][] dist = new int[board.length][board.length][4];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				Arrays.fill(dist[i][j], -1);
			}
		}
		return dist;
	}

	class Point {
		int x, y, dir;

		public Point(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
