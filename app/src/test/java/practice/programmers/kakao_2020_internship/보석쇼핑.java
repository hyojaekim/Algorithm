package practice.programmers.kakao_2020_internship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class 보석쇼핑 {

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
		Set<String> set = new HashSet<>(Arrays.asList(gems)); //모든 보석 종류들을 담는다.
		Map<String, Integer> map = new HashMap<>(); //보석 빈도수를 관리한다.
		Queue<String> q = new LinkedList<>(); //쓸어 담은 보석들을 담는다.
		int start = 0;
		int startIndex = 0;
		int length = gems.length + 1;

		//모든 보석들을 확인한다.
		for (String gem : gems) {
			if (map.containsKey(gem)) map.put(gem, map.get(gem) + 1); //발견한 보석 개수를 증가시킨다.
			else map.put(gem, 1);

			q.offer(gem); //큐에 보석 추가

			while (!q.isEmpty()) { //가장 앞에 있는 보석을 뺄 수 있을때까지 뺀다.
				String temp = q.peek();
				if (map.get(temp) > 1) {
					map.put(temp, map.get(temp) - 1);
					q.poll();
					start++;
				} else break;
			}

			if (map.size() != set.size()) continue; //모든 종류 보석을 담지 못하면 계속 담는다.
			if (length > q.size()) { //최소로 모두 쓸어담을 수 있으면 저장
				startIndex = start;
				length = q.size();
			}
		}
		return new int[]{startIndex + 1, startIndex + length};
	}
}
