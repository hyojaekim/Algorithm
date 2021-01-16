package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 압축_3차 {

	@Test
	void test() {
		int[] solution = solution("KAKAO");
		Assertions.assertArrayEquals(solution, new int[]{11, 1, 27, 15});
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/17684
	 * [분류] 구현
	 * [풀이]
	 * 1. A-Z까지 모든 문자열 초기화
	 * 2. dic에 없는 위치를 찾는다.
	 * 3. dic에 추가한다. (max + 1)
	 * 4. result에 색인 번호를 추가한다.
	 *
	 * @param msg 문자열
	 * @return 위 조건에 맞게 출력하는 색인 번호들을 반환하라.
	 */
	public int[] solution(String msg) {
		List<Integer> result = new ArrayList<>();
		int max = 26;
		Map<String, Integer> dic = init();

		for (int i = 0; i < msg.length(); i++) {
			int nextIdx = i;
			for (int j = i + 1; j < msg.length(); j++) { // i~j까지 문자열이 dic에 없을때 까지 찾는다.
				String key = msg.substring(i, j + 1);
				if (!dic.containsKey(key)) {
					dic.put(key, ++max);
					nextIdx = j;
					break;
				} else if (j == msg.length() - 1) nextIdx = j + 1;
			}
			nextIdx += nextIdx == i ? 1 : 0;
			String key = msg.substring(i, nextIdx); //색인 번호 넣기
			result.add(dic.get(key));
			i += key.length() - 1;
		}

		int[] answer = new int[result.size()]; //List -> new int[]
		for (int i = 0; i < result.size(); i++) {
			answer[i] = result.get(i);
		}
		return answer;
	}

	private Map<String, Integer> init() {
		Map<String, Integer> result = new HashMap<>();
		for (int i = 0; i < 26; i++) {
			result.put(String.valueOf((char) ('A' + i)), i + 1);
		}
		return result;
	}
}
