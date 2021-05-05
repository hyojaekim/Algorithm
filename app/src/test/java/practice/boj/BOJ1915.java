package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1915 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int n = Integer.parseInt(split[0]) + 1;
		int m = Integer.parseInt(split[1]) + 1;
		int[][] dp = new int[n][m];
		int max = 0;
		for (int i = 1; i < n; i++) {
			String line = br.readLine();
			for (int j = 1; j < m; j++) {
				dp[i][j] = line.charAt(j - 1) == '0' ? 0 : 1;
				if (dp[i][j] == 0) continue;
				int min = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
				min = (int) Math.sqrt(min) + 1;
				dp[i][j] = min * min;
				max = Math.max(max, dp[i][j]);
			}
		}
		System.out.println(max);
	}
}
