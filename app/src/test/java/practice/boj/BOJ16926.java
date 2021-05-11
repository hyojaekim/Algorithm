package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class BOJ16926 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int n = Integer.parseInt(split[0]);
		int m = Integer.parseInt(split[1]);
		int r = Integer.parseInt(split[2]);
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			split = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		for (int k = 0; k < r; k++) {
			int length = Math.min(n, m) / 2;
			for (int i = 0; i < length; i++) {
				rotation(map,n - i, m - i, i, i);
			}
		}

		for (int i = 0; i < n; i++) {
			StringJoiner sj = new StringJoiner(" ");
			for (int j = 0; j < m; j++) {
				sj.add(String.valueOf(map[i][j]));
			}
			System.out.println(sj);
		}
	}

	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	private static void rotation(int[][] map, int maxX, int maxY, int nx, int ny) {
		int before = map[nx][ny];
		int minX = nx, minY = ny;
		for (int dir = 0; dir < 4; dir++) {
			while (!isBoundary(nx, ny, minX, minY, maxX, maxY, dir)) {
				nx += dx[dir];
				ny += dy[dir];
				int temp = map[nx][ny];
				map[nx][ny] = before;
				before = temp;
			}
		}
	}

	private static boolean isBoundary(int nx, int ny, int minX, int minY, int maxX, int maxY, int dir) {
		return nx + dx[dir] < minX || ny + dy[dir] < minY || nx + dx[dir] >= maxX || ny + dy[dir] >= maxY;
	}
}
