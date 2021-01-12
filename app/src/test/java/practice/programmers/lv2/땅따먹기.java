package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class 땅따먹기 {

	@Test
	void test() {
		int solution = solution(new int[][]{{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}});
		Assertions.assertEquals(solution, 16);
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/12913
	 * [분류] 일반
	 * [풀이]
	 * 1. dfs -> 시간 초과
	 * 2. 이전 행에서 같은 열이 아닌 가장 큰수를 더한다.
	 *
	 * @param land 2차원 배열 (n x 4)
	 * @return 같은 열로 내려가지 않고, 가장 최고점을 구하라
	 */
	public int solution(int[][] land) {
		for (int i = 1; i < land.length; i++) {
			int[] beforeLand = land[i - 1];
			land[i][0] += Math.max(Math.max(beforeLand[1], beforeLand[2]), beforeLand[3]);
			land[i][1] += Math.max(Math.max(beforeLand[0], beforeLand[2]), beforeLand[3]);
			land[i][2] += Math.max(Math.max(beforeLand[0], beforeLand[1]), beforeLand[3]);
			land[i][3] += Math.max(Math.max(beforeLand[0], beforeLand[1]), beforeLand[2]);
		}

		int result = 0;
		for (int i = 0; i < 4; i++) result = Math.max(result, land[land.length - 1][i]);
		return result;
	}
}
