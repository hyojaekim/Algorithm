package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ16973 {

	@Test
	void test() {
		int solution = solution(4, 5, new int[][]{
						{0, 0, 0, 0, 0},
						{0, 0, 1, 0, 0},
						{0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0}
		}, 2, 2, 0, 0, 0, 3);

		Assertions.assertEquals(solution, 7);
	}

	@Test
	void input() {
//		Main main = new Main();
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int n = Integer.parseInt(st.nextToken());
//		int m = Integer.parseInt(st.nextToken());
//		int[][] map = new int[n][m];
//		for (int i = 0; i < n; i++) {
//			st = new StringTokenizer(br.readLine());
//			for (int j = 0; j < m; j++) {
//				map[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}
//		st = new StringTokenizer(br.readLine());
//		int h = Integer.parseInt(st.nextToken());
//		int w = Integer.parseInt(st.nextToken());
//		int startX = Integer.parseInt(st.nextToken());
//		int startY = Integer.parseInt(st.nextToken());
//		int endX = Integer.parseInt(st.nextToken());
//		int endY = Integer.parseInt(st.nextToken());
//
//		int solution = main.solution(n, m, map, h, w, startX - 1, startY - 1, endX -1, endY - 1);
//		System.out.println(solution);
	}

	int[] dx = {0, 1, 0, -1};
	int[] dy = {1, 0, -1, 0};
	int[] dirX = {1, 0, 1, 0};
	int[] dirY = {0, 1, 0, 1};

	int n, m, h, w;

	/**
	 * [문제] https://www.acmicpc.net/problem/16973
	 * [분류] 그래프, BFS
	 * [삽질]
	 * 1. +를 해야 하는데 +를 빼먹어서 한참동안 삽질했다..
	 *
	 * @param n 전체 세로 길이
	 * @param m 전체 가로 길이
	 * @param map 0:빈칸, 1:벽
	 * @param h 직사각형 세로 길이
	 * @param w 직사각형 가로 길이
	 * @param startX 왼쪽 위 x
	 * @param startY 왼쪽 위 y
	 * @param endX 도착 x
	 * @param endY 도착 y
	 * @return 최소 이동 거리
	 */
	public int solution(int n, int m, int[][] map, int h, int w, int startX, int startY, int endX, int endY) {
		int[][] dist = new int[n][m];
		this.n = n; this.m = m; this.h = h; this.w = w;
		for (int[] temp : dist) Arrays.fill(temp, -1);
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(startX, startY));
		dist[startX][startY] = 0;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			int curDist = dist[cur.x][cur.y];
			if (cur.x == endX && cur.y == endY) return curDist;

			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				int nex = nx + this.h - 1;
				int ney = ny + this.w - 1;

				if (nx < 0 || ny < 0 || nx >= this.n || ny >= this.m) continue;
				if (nex < 0 || ney < 0 || nex >= this.n || ney >= this.m) continue;
				if (dist[nx][ny] != -1) continue;
				if (!canMove(dir, nx, ny, map)) continue;

				dist[nx][ny] = curDist + 1;
				q.offer(new Point(nx, ny));
			}
		}
		return -1;
	}

	private boolean canMove(int curDir, int x, int y, int[][] map) {
		int max = (curDir == 0 || curDir == 2) ? h - 1 : w - 1;
		if (curDir == 0) y += w - 1;
		else if (curDir == 1) x += h - 1;

		if (map[x][y] == 1) return false;
		for (int i = 0; i < max; i++) {
			x += dirX[curDir];
			y += dirY[curDir];
			if (map[x][y] == 1) return false;
		}
		return true;
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
