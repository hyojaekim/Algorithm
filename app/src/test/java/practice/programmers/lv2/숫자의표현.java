package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class 숫자의표현 {

	@Test
	void test() {
		int solution = solution(15);
		Assertions.assertEquals(solution, 4);
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/12924
	 *
	 * @param n 숫자 n
	 * @return 연속한 자연수를 더해서 n이 가능한 방법 카운트 하기.
	 */
	public int solution(int n) {
		int result = 0;
		int sum;
		for (int i = 1; i <= n; i++) {
			sum = 0;
			for (int j = i; j <= n; j++) {
				sum += j;
				if (sum > n || sum == n) break;
			}
			if (sum == n) result++;
		}
		return result;
	}
}
