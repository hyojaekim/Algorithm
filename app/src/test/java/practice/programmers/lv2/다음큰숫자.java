package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class 다음큰숫자 {

	@Test
	void test() {
		int solution = solution(78);
		Assertions.assertEquals(solution, 83);
	}

	@Test
	void test2() {
		int solution = solution2(78);
		Assertions.assertEquals(solution, 83);
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/12911
	 * [분류] 2진수
	 * [조건]
	 * 1. n보다 클 것.
	 * 2. 2진수 변환했을 때, 1의 개수가 같을 것.
	 * 3. 1번 2번 조건을 만족하는 가장 작은 숫자일 것.
	 *
	 * @param n 숫자
	 * @return 주어진 숫자보다 모든 조건에 만족하는 다음 큰 숫자를 구하라.
	 */
	public int solution(int n) {
		int result = n + 1;
		int cnt = 0;
		for (char c : Integer.toBinaryString(n).toCharArray()) {
			if (c == '1') cnt++;
		}

		while (impossible(result, cnt)) result++;
		return result;
	}

	private boolean impossible(int result, int cnt) {
		int count = 0;
		for (char c : Integer.toBinaryString(result).toCharArray()) {
			if (c == '1') count++;
			if (count > cnt) return true;
		}
		return count != cnt;
	}

	public int solution2(int n) {
		int cnt = Integer.bitCount(n);
		while (Integer.bitCount(++n) != cnt) {}
		return n;
	}
}
