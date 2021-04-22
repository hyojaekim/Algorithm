package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//public class Main {
public class BOJ3055 {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int n = Integer.parseInt(split[0]);
		int m = Integer.parseInt(split[1]);

		String[] map = new String[n];
		List<Point> waters = new ArrayList<>();
		Point start = null, end = null;
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine();
			for (int j = 0; j < m; j++) {
				if (map[i].charAt(j) == 'S') {
					start = new Point(i, j);
				} else if (map[i].charAt(j) == 'D') {
					end = new Point(i, j);
				} else if (map[i].charAt(j) == '*') {
					waters.add(new Point(i, j));
				}
			}
		}

		BOJ3055 main = new BOJ3055();
//		Main main = new Main();

		String solution = main.solution(n, m, start, end, waters, map);
		System.out.println(solution);
	}
	int[] dx = {0, 1, 0, -1};
	int[] dy = {1, 0, -1, 0};

	public String solution(int n, int m, Point start, Point end, List<Point> waters, String[] map) {
		int[][] waterDist = waterBFS(n, m, waters, map);
		int[][] dist = new int[n][m];
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		dist[start.x][start.y] = 1;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			int curDist = dist[cur.x][cur.y];
			if (cur.x == end.x && cur.y == end.y) {
				return String.valueOf(curDist - 1);
			}

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (map[nx].charAt(ny) == 'X') continue;
				if (dist[nx][ny] != 0) continue;
				if (waterDist[nx][ny] != 0 && curDist + 1 >= waterDist[nx][ny]) continue;
				q.offer(new Point(nx, ny));
				dist[nx][ny] = dist[cur.x][cur.y] + 1;
			}
		}
		return "KAKTUS";
	}

	private int[][] waterBFS(int n, int m, List<Point> waters, String[] map) {
		int[][] dist = new int[n][m];
		Queue<Point> q = new LinkedList<>();
		for (Point water : waters) {
			dist[water.x][water.y] = 1;
			q.offer(water);
		}

		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (map[nx].charAt(ny) == 'X' || map[nx].charAt(ny) == 'D') continue;
				if (dist[nx][ny] != 0) continue;
				q.offer(new Point(nx, ny));
				dist[nx][ny] = dist[cur.x][cur.y] + 1;
			}
		}
		return dist;
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

