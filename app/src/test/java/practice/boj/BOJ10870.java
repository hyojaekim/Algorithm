package practice.boj;

import org.junit.jupiter.api.Test;

public class BOJ10870 {

	@Test
	void test() {
		int solution = solution(20);
		System.out.println(solution);
	}

	public int solution(int n) {
		int[] dp = new int[100];
		dp[1] = 1;

		return fibo(n, dp);
	}

	public int fibo(int num, int[] dp) {
		if (num < 1) return 0;
		if (num == 1) return 1;
		if (dp[num] != 0) return dp[num];

		dp[num] =  fibo(num - 1, dp) + fibo(num - 2, dp);
		return dp[num];
	}
}
