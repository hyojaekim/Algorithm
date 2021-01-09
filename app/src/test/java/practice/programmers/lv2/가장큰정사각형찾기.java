package practice.programmers.lv2;

import org.junit.jupiter.api.Test;

public class 가장큰정사각형찾기 {

	@Test
	void test() {
		int result = solution(new int[][]{{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 0}});
		System.out.println(result);
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/12905
	 * 왼, 위, 왼위를 확인하며 정사각형의 최대 넓이를 구한다.
	 *
	 * @param board 사각형, 0과 1로 이루어진 보드
	 * @return 최대 넓이를 가지는 정사각형을 반환한다.
	 */
	public int solution(int[][] board) {
		int maxArea = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 0) continue;
				if (maxArea == 0) maxArea = 1;
				if (j - 1 < 0 || i - 1 < 0) continue; //왼, 위, 왼위 참조 가능한지 확인

				int left = board[i][j - 1];
				int up = board[i - 1][j];
				int leftUp = board[i - 1][j - 1];

				if (left == 0 || up == 0 || leftUp == 0) { //왼, 위, 왼위 중에 하나라도 0이라면 패스
					continue;
				} else if (left == up && up == leftUp) { //왼, 위, 왼위가 모두 같으면 +1
					board[i][j] = left + 1;
				} else { //하나라도 같이 않으면, 최소값 + 1
					int min = Math.min(left, up);
					min = Math.min(min, leftUp);
					board[i][j] = min + 1;
				}
				maxArea = Math.max(maxArea, board[i][j]);
			}
		}
		return maxArea * maxArea;
	}
}
