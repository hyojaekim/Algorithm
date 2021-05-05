package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11660 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int n = Integer.parseInt(split[0]) + 1;
		int m = Integer.parseInt(split[1]);

		int[][] dp = new int[n][n];
		for (int i = 1; i < n; i++) {
			split = br.readLine().split(" ");
			for (int j = 1; j < n; j++) {
				int num = Integer.parseInt(split[j - 1]);
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + num;
			}
		}

		for (int i = 0; i < m; i++) {
			split = br.readLine().split(" ");
			int x1 = Integer.parseInt(split[0]);
			int y1 = Integer.parseInt(split[1]);
			int x2 = Integer.parseInt(split[2]);
			int y2 = Integer.parseInt(split[3]);

			int result = dp[x2][y2] - (dp[x1 - 1][y2] + dp[x2][y1 - 1] - dp[x1 - 1][y1 - 1]);
			System.out.println(result);
		}
	}
}
