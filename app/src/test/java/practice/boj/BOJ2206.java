package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2206 {

	@Test
	void test() {
		int solution = solution(6, 4, new int[][]{
						{0, 1, 0, 0},
						{1, 1, 1, 0},
						{1, 0, 0, 0},
						{0, 0, 0, 0},
						{0, 1, 1, 1},
						{0, 0, 0, 0},
		});

		Assertions.assertEquals(solution, 15);
	}

	@Test
	void test2() {
		int solution = solution(6, 4, new int[][]{
						{0, 0, 0, 0},
						{1, 1, 1, 0},
						{1, 0, 0, 0},
						{0, 0, 0, 0},
						{0, 1, 1, 1},
						{0, 0, 0, 0},
		});

		Assertions.assertEquals(solution, 9);
	}

	@Test
	void test3() {
		int solution = solution(4, 4, new int[][]{
						{0, 1, 1, 1},
						{1, 1, 1, 1},
						{1, 1, 1, 1},
						{1, 1, 1, 0}
		});

		Assertions.assertEquals(solution, -1);
	}

	int[] dx = {0, 1, 0, -1};
	int[] dy = {1, 0, -1, 0};

	/**
	 * [문제] https://www.acmicpc.net/problem/2206
	 * [분류] 그래프, BFS
	 * [삽질]
	 * 1. 결과값 반환할 때 -1을 생각하지 못하고, 최소값을 반환했다.
	 * 2. 벽을 이전에 부시지 않고, 방문하지 않아야 하는데 방문 체크를 빼먹었다.
	 *
	 * @param n
	 * @param m
	 * @param map
	 * @return
	 */
	public int solution(int n, int m, int[][] map) {
		int[][][] dist = initDist(n, m);
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0, false));

		int result = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			int curWall = cur.isBrokeWall ? 1 : 0;
			int curDist = dist[cur.x][cur.y][curWall];

			if (cur.x == n - 1 && cur.y == m - 1) {
				result = Math.min(result, curDist);
			}

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (map[nx][ny] == 1) {
					if (!cur.isBrokeWall && dist[nx][ny][1] == -1) {
						q.offer(new Point(nx, ny, true));
						dist[nx][ny][1] = curDist + 1;
					}
					continue;
				}
				if (dist[nx][ny][curWall] != -1) continue;
				q.offer(new Point(nx, ny, cur.isBrokeWall));
				dist[nx][ny][curWall] = curDist + 1;
			}
		}
		return result == Integer.MAX_VALUE ? -1 : result;
	}

	private int[][][] initDist(int n, int m) {
		int[][][] result = new int[n][m][2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				Arrays.fill(result[i][j], -1);
			}
		}
		result[0][0][0] = 1;
		return result;
	}

	static class Point {
		int x, y;
		boolean isBrokeWall;

		public Point(int x, int y, boolean isBrokeWall) {
			this.x = x;
			this.y = y;
			this.isBrokeWall = isBrokeWall;
		}
	}
}
