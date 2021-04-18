package practice.programmers.lv3;

public class 등굣길 {

	public int solution(int m, int n, int[][] puddles) {
		int[][] dist = new int[n][m];
		dist[0][0] = 1;

		for (int[] puddle : puddles) {
			dist[puddle[1] - 1][puddle[0] - 1] = -1;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (dist[i][j] == -1) continue;
				if (i - 1 >= 0 && dist[i - 1][j] != -1) dist[i][j] += dist[i - 1][j];
				if (j - 1 >= 0 && dist[i][j - 1] != -1) dist[i][j] += dist[i][j - 1];
				dist[i][j] %= 1_000_000_007;
			}
		}
		return dist[n - 1][m - 1];
	}
}
