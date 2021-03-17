package practice.boj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ14940 {

	int[] dx = {0, 1, 0, -1};
	int[] dy = {1, 0, -1, 0};

	/**
	 * [문제] https://www.acmicpc.net/problem/14940
	 * [분류] 그래프, BFS
	 *
	 * @param n 세로 길이
	 * @param m 가로 길이
	 * @param map 0:벽, 1:빈칸, 2:시작점
	 * @param startX 시작점 x
	 * @param startY 시작점 y
	 * @return 목표지점까지 최단 거리를 구하라
	 */
	public int[][] solution(int n, int m, int[][] map, int startX, int startY) {
		int[][] dist = new int[n][m];
		for (int i = 0; i < n; i++) Arrays.fill(dist[i], -1);
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(startX, startY));
		dist[startX][startY] = 0;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (dist[nx][ny] != -1 || map[nx][ny] == 0) continue;
				dist[nx][ny] = dist[cur.x][cur.y] + 1;
				q.offer(new Point(nx, ny));
			}
		}
		return dist;
	}

	class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
