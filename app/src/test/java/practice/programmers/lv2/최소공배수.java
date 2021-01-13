package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class 최소공배수 {

	@Test
	void test() {
		int solution = solution(new int[]{2, 6, 8, 14});
		Assertions.assertEquals(solution, 168);

		int solution2 = solution(new int[]{1, 2, 3});
		Assertions.assertEquals(solution2, 6);

		int solution3 = solution(new int[]{8});
		Assertions.assertEquals(solution3, 8);

		int solution4 = solution(new int[]{100, 100, 100});
		Assertions.assertEquals(solution4, 100);
	}

	int[] arr;
	Map<Integer, Integer> map;

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/12953
	 * [분류] 구현
	 *
	 * @param arr n개의 숫자들
	 * @return 최소공배수를 구하라.
	 */
	public int solution(int[] arr) {
		int result = 1;
		this.arr = arr;
		this.map = new HashMap<>();
		for (int number : arr) {
			calculate(number);
		}
		for (Integer key : map.keySet()) {
			for (int i = 0; i < map.get(key); i++) {
				result *= key;
			}
		}
		return result;
	}

	private void calculate(int number) {
		Map<Integer, Integer> numbers = new HashMap<>();
		int n = 2;
		while (number > 1) {
			if (number % n != 0) {
				n++;
				continue;
			}

			if (numbers.containsKey(n)) {
				numbers.put(n, numbers.get(n) + 1);
			} else {
				numbers.put(n, 1);
			}
			number = number / n;
		}

		for (Integer key : numbers.keySet()) {
			if (map.containsKey(key)) {
				Integer a = numbers.get(key);
				Integer b = map.get(key);
				map.put(key, Math.max(a, b));
			} else {
				map.put(key, numbers.get(key));
			}
		}
	}
}
