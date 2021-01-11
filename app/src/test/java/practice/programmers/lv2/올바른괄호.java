package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class 올바른괄호 {

	@Test
	void test() {
		boolean s1 = solution("()()");
		boolean s2 = solution("(())()");
		boolean s3 = solution(")()(");
		boolean s4 = solution("(()(");

		Assertions.assertTrue(s1);
		Assertions.assertTrue(s2);
		Assertions.assertFalse(s3);
		Assertions.assertFalse(s4);
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/12909
	 * [분류] 문자열
	 * [효율성] solution: 6.11ms, 7.13ms / solution2: 16.32ms, 23.08ms
	 *
	 * @param s '(' or ')'로 이루어진 문자열
	 * @return 괄호가 바르게 짝지어졌는가?
	 */
	public boolean solution(String s) {
		int cnt = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ')') {
				if (cnt == 0) return false;
				cnt--;
			} else cnt++;
		}
		return cnt == 0;
	}

	public boolean solution2(String s) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ')') {
				if (stack.isEmpty()) return false;
				stack.pop();
			} else {
				stack.push(0);
			}
		}
		return stack.isEmpty();
	}
}
