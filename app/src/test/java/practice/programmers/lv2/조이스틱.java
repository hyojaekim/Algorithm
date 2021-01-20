package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class 조이스틱 {
	@Test
	void test() {
		int result = solution("JAN");
		Assertions.assertEquals(result, 23);
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/42860#
	 * [분류] 그리디
	 * [시간] 85분
	 * [틀렸던 이유]
	 * 1. 오른쪽 끝에 도달하면 다시 처음부터 이동하는 것을 누락함.
	 * 2. 오른쪽 및 왼쪽 이동 횟수 구하는 로직에서 오류
	 *
	 * @param reqName 문자열
	 * @return AAA..에서 주어진 문자열로 완성할 수 있도록 조이스틱을 최소한으로 움직인 횟수를 구하라.
	 */
	public int solution(String reqName) {
		int result = 0;
		char[] name = reqName.toCharArray();
		boolean[] visited = new boolean[name.length];
		int cnt = 0;
		for (int i = 0; i < name.length; i++) {
			if (name[i] == 'A') {
				visited[i] = true;
			} else {
				result += Math.min(name[i] - 'A', 'Z' - name[i] + 1);
				cnt++;
			}
		}
		if (name[0] != 'A') {
			visited[0] = true;
			cnt--;
		}

		int idx = 0;
		while (cnt > 0) {
			int leftIndex = idx;
			int rightIndex = idx;
			int left = Integer.MAX_VALUE;
			int right = left;

			while (visited[rightIndex]) { //오른쪽으로 가면서 방문하지 않은곳 찾기
				if (rightIndex + 1 >= visited.length) {
					rightIndex = -1;
				}
				rightIndex++;
			}

			while (visited[leftIndex]) { //왼쪽으로 가면서 방문하지 않은곳 찾기
				if (leftIndex - 1 < 0) {
					leftIndex = visited.length;
				}
				leftIndex--;
			}

			//왼쪽, 오른쪽으로 움직인 횟수 구하기
			if (!visited[rightIndex]) right = idx > rightIndex ? visited.length - idx + rightIndex : rightIndex - idx;
			if (!visited[leftIndex]) left = idx > leftIndex ? idx - leftIndex : idx + visited.length - leftIndex;

			if (right <= left) {
					idx = rightIndex;
					result += right;
			} else {
					idx = leftIndex;
					result += left;
			}
			cnt--;
			visited[idx] = true;
		}
		return result;
	}
}
