package practice.programmers.kakao_2020_internship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class 보석쇼핑_효율성실패 {

	@Test
	void test() {
		int[] solution = solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
		Assertions.assertArrayEquals(solution, new int[]{3, 7});
	}

	public int[] solution(String[] gems) {
		int[] answer = new int[]{0, gems.length - 1};
		Map<String, List<Integer>> gemsMap = init(gems);
		int length = gems.length - gemsMap.size() + 1;
		int max = gems.length;
		int end;
		for (int i = 0; i < length; i++) {
			end = i + max - 1;
			if (containsAll(gemsMap, i, end)) {
				max--;
				answer[0] = i;
				answer[1] = i + max - 1;
				i--;
			}
			if (max <= gemsMap.size()) break;
		}
		answer[0]++;
		answer[1]++;
		return answer;
	}

	private Map<String, List<Integer>> init(String[] gems) {
		Map<String, List<Integer>> result = new HashMap<>();
		for (int i = 0; i < gems.length; i++) {
			result.computeIfAbsent(gems[i], k -> new ArrayList<>()).add(i);
		}
		return result;
	}

	private boolean containsAll(Map<String, List<Integer>> gemsMap, int start, int max) {
		for (String key : gemsMap.keySet()) {
			if (!found(gemsMap.get(key), start, max)) return false;
		}
		return true;
	}

	private boolean found(List<Integer> numbers, int min, int max) {
		if (numbers.get(0) >= max || numbers.get(numbers.size() - 1) < min) return false;
		for (Integer index : numbers) {
			if (index >= min && index < max) return true;
		}
		return false;
	}
}
