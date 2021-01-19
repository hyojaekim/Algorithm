package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class 쿼드압축후개수세기 {

	@Test
	void test() {
		int[] solution = solution(new int[][]{{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1}});
		Assertions.assertArrayEquals(solution, new int[]{4, 9});
	}

	int[] answer; //0의 개수, 1의 개수
	int[][] arr;
	public int[] solution(int[][] arr) {
		answer = new int[2];
		this.arr = arr;

		dfs(arr.length, 0, 0);
		return answer;
	}

	private void dfs(int n, int startX, int startY) {
		if (isPossible(n, startX, startY)) {
			answer[arr[startX][startY]]++;
		} else {
			n = n / 2;
			dfs(n, startX, startY);
			dfs(n, startX, startY + n);
			dfs(n, startX + n, startY);
			dfs(n, startX + n, startY + n);
		}
	}

	private boolean isPossible(int n, int startX, int startY) {
		if (n == 1) return true;
		int number = arr[startX][startY];

		for (int i = startX; i < startX + n; i++) {
			for (int j = startY; j < startY + n; j++) {
				if (number != arr[i][j]) return false;
			}
		}
		return true;
	}
}
