package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

public class JadenCase문자열만들기 {

	@Test
	void test() {
//		String s1 = solution("3people unFollowed me");
//		String s2 = "3people Unfollowed Me";
//		Assertions.assertEquals(s1, s2);
//
//		String s3 = solution("for the last week");
//		String s4 = "For The Last Week";
//		Assertions.assertEquals(s3, s4);

		String s5 = solution("abc hello   hello");
		String s6 = "Abc Hello   Hello";
		Assertions.assertEquals(s5, s6);

	}

	/**
	 * [분류] 문자열
	 * 공백으로 구분되어 있다.
	 * ex) aaaa aaa -> replace 조심
	 * ex) abc^hello^^^hello -> Abc^Hello^^^Hello
	 * ex) abc^hello^^^hello^ -> Abc^Hello^^^Hello^
	 *
	 * @param s 문자열
	 * @return 모든 단어의 첫 문자를 대문자로 바꾸어 리턴하라.
	 */
	public String solution(String s) {
		StringJoiner result = new StringJoiner(" ");
		for (String str : s.split(" ")) {
			if (str.isEmpty()) {
				result.add("");
				continue;
			}
			str = str.toLowerCase();
			if (str.charAt(0) >= 'a' && str.charAt(0) <= 'z') {
				char[] chars = str.toCharArray();
				chars[0] = String.valueOf(chars[0]).toUpperCase().charAt(0);
				result.add(new String(chars));
			} else result.add(str);
		}
		if (s.charAt(s.length() - 1) == ' ') result.add("");
		return result.toString();
	}
}
