package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BOJ14719 {

	@Test
	void test() {
//		int solution = solution(4, 4, new int[]{3, 0, 1, 4});
		int solution = solution(4, 8, new int[]{3, 1, 2, 3, 4, 1, 1, 2});
		Assertions.assertEquals(solution, 5);
	}

	/**
	 * [문제] https://www.acmicpc.net/problem/14719
	 * [분류] 구현, 시뮬레이션
	 * [풀이]
	 * 1. 블럭의 크기가 가장 큰 순서로 정렬한다.
	 * 2. 현재 위치에서 왼쪽, 오른쪽에서 가장 블록이 큰 위치를 선택한다.
	 * 3. 가장 작은 블럭 - 현재 위치의 블록 개수를 계산한다. (0 이하 카운트 x)
	 *
	 * @param h 세로 길이
	 * @param w 가로 길이
	 * @param blockInfo 블럭 개수
	 * @return 빗물이 고이는 구역의 개수를 구하라
	 */
	public int solution(int h, int w, int[] blockInfo) {
		int count = 0;
		List<Integer> sortedBlockIndex = sort(blockInfo);

		for (int i = 0; i < w; i++) {
			int maxRightIndex = findMaxIndex(i + 1, w, i, blockInfo, sortedBlockIndex);
			int maxLeftIndex = findMaxIndex(0, i, i, blockInfo, sortedBlockIndex);
			if (maxRightIndex == -1 || maxLeftIndex == -1) continue;

			int height = Math.min(blockInfo[maxRightIndex], blockInfo[maxLeftIndex]);
			count += Math.max(height - blockInfo[i], 0);
		}
		return count;
	}

	public List<Integer> sort(int[] blocks) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < blocks.length; i++) {
			result.add(i);
		}

		result.sort((o1, o2) -> {
			int num = blocks[o2] - blocks[o1];
			return num == 0 ? o1 - o2 : num;
		});
		return result;
	}

	private int findMaxIndex(int start, int end, int curIndex, int[] blockInfo, List<Integer> sortedBlockIndex) {
		if (start < 0 || end > sortedBlockIndex.size()) return -1;

		for (Integer index : sortedBlockIndex) {
			if (index < start || index >= end) continue;
			if (blockInfo[index] <= blockInfo[curIndex]) continue;
			return index;
		}
		return -1;
	}
}
