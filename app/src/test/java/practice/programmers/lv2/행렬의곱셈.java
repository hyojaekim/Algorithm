package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class 행렬의곱셈 {

	@Test
	void test() {
		int[][] solution = solution(new int[][]{{1, 4}, {3, 2}, {4, 1}}, new int[][]{{3, 3}, {3, 3}});
		Assertions.assertEquals(solution, new int[][]{{15, 15}, {15, 15}, {15, 15}});
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/12949
	 * [분류] 행렬
	 *
	 * @param arr1 첫 번째 행렬
	 * @param arr2 두 번째 행렬
	 * @return 행렬의 곱을 구하라.
	 */
	public int[][] solution(int[][] arr1, int[][] arr2) {
		int m = arr1.length;
		int n = arr2[0].length;
		int[][] answer = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				answer[i][j] = calculate(arr1, arr2, i, j);
			}
		}
		return answer;
	}

	private int calculate(int[][] arr1, int[][] arr2, int x, int y) {
		int result = 0;
		for (int i = 0; i < arr1[x].length; i++) {
			result += arr1[x][i] * arr2[i][y];
		}
		return result;
	}
}
