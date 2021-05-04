package practice.boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ4485 {

	/**
	 * 최소 금액
	 * -> 검정색 루피 획득 -> 소지한 루피 감소
	 * -> 검정색 루피가 가득 -> 제일 왼쪽 위에 있음 [0][0]
	 * -> 가장 오른쪽 아래로 이동
	 * <p>
	 * 가장 최소 금액을 구한다.
	 * -> BFS를 수행한다.
	 * -> 범위, cost -> 최소
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int caseCount = 1;
		while (true) {
			int n = Integer.parseInt(sc.nextLine());
			if (n == 0) break;

			int[][] map = new int[n][n];
			int[][] cost = new int[n][n];
			for (int i = 0; i < n; i++) {
				String[] split = sc.nextLine().split(" ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(split[j]);
					cost[i][j] = -1;
				}
			}
			int result = solution(n, map, cost);
			System.out.println("Problem " + caseCount + ": " + result);
			caseCount++;
		}
	}

	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	public static int solution(int n, int[][] map, int[][] cost) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));
		cost[0][0] = map[0][0];

		while (!q.isEmpty()) {
			Point cur = q.poll();
			int curCost = cost[cur.x][cur.y];

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
				if (cost[nx][ny] == -1 || cost[nx][ny] > map[nx][ny] + curCost) {
					cost[nx][ny] = curCost + map[nx][ny];
					q.offer(new Point(nx, ny));
				}
			}
		}
		return cost[n - 1][n - 1];
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
