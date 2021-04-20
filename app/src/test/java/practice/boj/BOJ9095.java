package practice.boj;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9095 {

	@Test
	void test() {
		int[] solution = solution();
		for (int i = 0; i < solution.length; i++) {
			System.out.println(i + " -> " + solution[i]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCount = Integer.parseInt(br.readLine());
		int[] dp = solution();

		while (testCount > 0) {
			testCount--;
			int n = Integer.parseInt(br.readLine());
			System.out.println(dp[n]);
		}
	}

	public static int[] solution() {
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
