package practice.boj;

import org.junit.jupiter.api.Test;

public class BOJ9095 {

	@Test
	void test() {
		int[] solution = solution();
		for (int i = 0; i < solution.length; i++) {
			System.out.println(i + " -> " + solution[i]);
		}
	}

	public int[] solution() {
		int[] dp = new int[12];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		for (int i = 4; i < dp.length; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}
		return dp;
	}

}
