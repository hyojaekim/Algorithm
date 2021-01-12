package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class 최댓값과최솟값 {

	@Test
	void test() {
		String solution = solution("1 2 3 4");
		String solution2 = solution("-1 -2 -3 -4");
		String solution3 = solution("-1 -1");

		Assertions.assertEquals(solution, "1 4");
		Assertions.assertEquals(solution2, "-4 -1");
		Assertions.assertEquals(solution3, "-1 -1");
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/12939
	 * [분류] 문자열
	 *
	 * @param s 공백으로 구분되는 숫자를 문자열로 표현
	 * @return "최소값 최댓값"으로 반환한다.
	 */
	public String solution(String s) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (String s1 : s.split(" ")) {
			int num = Integer.parseInt(s1);
			min = Math.min(min, num);
			max = Math.max(max, num);
		}
		return min + " " + max;
	}
}
