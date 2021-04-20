package practice.boj;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2579 {

	@Test
	void test() {
		int solution = solution(6, new int[]{
						10,
						20,
						15,
						25,
						10,
						20,
		});
		System.out.println(solution);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] steps = new int[n];
		for (int i = 0; i < n; i++) {
			steps[i] = Integer.parseInt(br.readLine());
		}
		int solution = solution(n, steps);
		System.out.println(solution);
	}

	public static int solution(int n, int[] steps) {
		if (n == 0) return 0;
		if (n == 1) return steps[0];
		if (n == 2) return steps[0] + steps[1];

		int[][] dp = new int[n][3];
		dp[0][1] = steps[0];
		dp[1][1] = steps[1];
		dp[1][2] = steps[0] + steps[1];

		for (int i = 2; i < n; i++) {
			dp[i][2] = dp[i - 1][1] + steps[i];
			dp[i][1] = Math.max(dp[i - 2][1], dp[i - 2][2]) + steps[i];
		}
		return Math.max(dp[n - 1][1], dp[n - 1][2]);
	}
}
