package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class 찍지어제거하기 {

	@Test
	void test() {
		int result = solution("cdcd");
		Assertions.assertEquals(result, 0);

		int result2 = solution("baabaa");
		Assertions.assertEquals(result2, 1);
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/12973
	 * [분류] stack
	 *
	 * @param s 문자열
	 * @return 2개씩 붙어있는 짝을 찾아 제거하여 모두 제거되었는지 확인하라.
	 */
	public int solution(String s) {
		Stack<Character> stack = new Stack<>();
		for (Character c : s.toCharArray()) {
			if (stack.isEmpty()) {
				stack.push(c);
			} else if (c == stack.peek()) {
				stack.pop();
			} else {
				stack.push(c);
			}
		}
		return stack.isEmpty() ? 1 : 0;
	}
}
