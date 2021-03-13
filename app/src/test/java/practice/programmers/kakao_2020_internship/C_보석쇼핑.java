package practice.programmers.kakao_2020_internship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class C_보석쇼핑 {

	@Test
	void test() {
		int[] solution = solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
		Assertions.assertArrayEquals(solution, new int[]{3, 7});

		int[] solution2 = solution(new String[]{"DIA"});
		Assertions.assertArrayEquals(solution2, new int[]{1, 1});

		int[] solution3 = solution(new String[]{"AA", "AB", "AC", "AA", "AC"});
		Assertions.assertArrayEquals(solution3, new int[]{1, 3});
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/67258
	 * [분류] 투포인터 및 구현
	 *
	 * @param gems 모든 보석
	 * @return 모든 종류의 보석을 최소로 쓸어담을 수 있는 위치를 구하라.
	 */
	public int[] solution(String[] gems) {
		Set<String> totalGem = new HashSet<>(Arrays.asList(gems));
		int max = totalGem.size();
		int start = 0, end = 0, resultStart = 0, resultEnd = gems.length - 1, resultSize = Integer.MAX_VALUE;
		Map<String, Integer> gemIndex = new HashMap<>();
		Map<Integer, Integer> gemCounter = new HashMap<>();
		int i = 0;
		for (String gem : totalGem) {
			gemIndex.put(gem, i);
			gemCounter.put(i, 0);
			i++;
		}

		int totalCount = 1;
		gemCounter.put(gemIndex.get(gems[0]), 1);
		while (start < gems.length) {
			if (totalCount == max) {
				if (resultSize > end - start) {
					resultStart = start;
					resultEnd = end;
					resultSize = end - start;
				}
				Integer idx = gemIndex.get(gems[start]);
				gemCounter.put(idx, gemCounter.get(idx) - 1);
				if (gemCounter.get(idx) == 0) totalCount--;
				start++;
			} else {
				end++;
				if (end >= gems.length) break;
				Integer idx = gemIndex.get(gems[end]);
				gemCounter.put(idx, gemCounter.get(idx) + 1);
				Integer count = gemCounter.get(idx);
				if (count == 1) totalCount++;
			}
		}
		return new int[]{resultStart + 1, resultEnd + 1};
	}
}
