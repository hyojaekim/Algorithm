package practice.programmers.kakao_2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class 신규아이디추천 {

	@Test
	void test() {
		Assertions.assertEquals(solution("...!@BaT#*..y.abcdefghijklm"), "bat.y.abcdefghi");
		Assertions.assertEquals(solution("\"z-+.^.\""), "z--");
		Assertions.assertEquals(solution("=.="), "aaa");
		Assertions.assertEquals(solution("123_.def"), "123_.def");
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/72410
	 * [분류] 문자열
	 *
	 * @param new_id 요청한 ID
	 * @return 문제에서 제시하는 7가지 단계를 거쳐 결과값을 반환하라.
	 */
	public String solution(String new_id) {
		new_id = new_id.toLowerCase();
		new_id = new_id.replaceAll("[^a-z|0-9|\\-|\\_|.]", "");
		while (new_id.contains("..")) {
			new_id = new_id.replace("..", ".");
		}
		if (new_id.length() > 0 && new_id.charAt(0) == '.') new_id = new_id.substring(1);
		if (new_id.length() > 0 && new_id.charAt(new_id.length() - 1) == '.') new_id = new_id.substring(0, new_id.length() - 1);

		if (new_id.isEmpty()) new_id += "a";
		if (new_id.length() >= 16) {
			new_id = new_id.substring(0, 15);
			if (new_id.charAt(new_id.length() - 1) == '.') new_id = new_id.substring(0, new_id.length() - 1);
		}
		if (new_id.length() <= 2) {
			char c = new_id.charAt(new_id.length() - 1);
			while (new_id.length() < 3) {
				new_id += c;
			}
		}
		return new_id;
	}
}
