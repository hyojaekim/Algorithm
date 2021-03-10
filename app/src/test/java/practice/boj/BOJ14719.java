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

	public int solution(int h, int w, int[] blockInfo) {
		int count = 0;
		List<Integer> sortedBlockIndex = sortBlocks(blockInfo);
		for (int i = 0; i < sortedBlockIndex.size(); i++) {
			int curIndex = sortedBlockIndex.get(i);
			if (blockInfo[curIndex] == 0) break;
			for (int j = i + 1; j < sortedBlockIndex.size(); j++) {
				int nextIndex = sortedBlockIndex.get(j);
				if (blockInfo[nextIndex] == 0) break;

				int max = Math.max(curIndex, nextIndex);
				int min = Math.min(curIndex, nextIndex);
				int height = blockInfo[nextIndex];

				List<Integer> temp = new ArrayList<>();
				for (int k = min + 1; k < max; k++) {
					temp.add(k);
					count += height - blockInfo[k];
				}
				sortedBlockIndex.removeAll(temp);
			}
		}
		return count;
	}

	public List<Integer> sortBlocks(int[] blocks) {
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < blocks.length; i++) {
			result.add(i);
		}

		result.sort((o1, o2) -> {
			int num = blocks[o2] - blocks[o1];
			if (num == 0) return o1 - o2;
			else return num;
		});
		return result;
	}
}
