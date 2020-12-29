package practice.book.coding_interview.chapter12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class 전화번호문자조합 {

	@Test
	void test() {
		List<String> solution = solution(Arrays.asList(2, 3));
		for (String s : solution) {
			System.out.println(s);
		}
		Assertions.assertEquals(solution.size(), 9);
	}

	/**
	 * 사용한 알고리즘 : 백트래킹
	 * 1. 2~9까지 숫자에 해당하는 문자 초기화
	 * 2. n(digits.size())개의 조합 구하기
	 * @param digits
	 * @return 전화 번호로 조합 가능한 모든 문자
	 */
	List<Integer> digits;
	Map<Integer, String> dic;
	List<String> result;
	public List<String> solution(List<Integer> digits) {
		this.digits = digits;
		this.dic = initDic();
		this.result = new ArrayList<>();
		dfs(0, "");
		return result;
	}

	private void dfs(int idx, String path) {
		if (path.length() == digits.size()) {
			result.add(path);
			return;
		}

		for (int i = idx; i < digits.size(); i++) {
			for (char c : dic.get(digits.get(i)).toCharArray()) {
				dfs(i + 1, path + c);
			}
		}
	}

	private Map<Integer, String> initDic() {
		return new HashMap<Integer, String>() {{
			put(2, "abc"); put(3, "def"); put(4, "ghi");
			put(5, "jkl"); put(6, "mno"); put(7, "pqrs");
			put(8, "tuv"); put(9, "wxyz");
		}};
	}
}
