package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class 피보나치수 {

	@Test
	void test() {
		int solution = solution(3);
		Assertions.assertEquals(solution, 2);
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/12945
	 * [분류] 재귀, DP
	 *
	 * @param n 숫자
	 * @return 피노나치 수를 구하라.
	 */
	public int solution(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i < dp.length; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
		}
		return dp[n];
	}
}
