package practice.book.coding_interview.chapter12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 조합의합 {

	@Test
	void test() {
		List<List<Integer>> solution = solution(Arrays.asList(2, 3, 6, 7), 7);
		Assertions.assertEquals(solution.size(), 2);
	}

	List<List<Integer>> result;
	List<Integer> candidates;

	/**
	 * 사용 알고리즘 : 조합(dfs)
	 * @param candidates 숫자 집합
	 * @param target 타겟 숫자
	 * @return 주어진 숫자로 더해서 target이 되는 원소를 나열하라.
	 */
	public List<List<Integer>> solution(List<Integer> candidates, int target) {
		this.result = new ArrayList<>();
		this.candidates = candidates;

		for (int i = 0; i < candidates.size(); i++) {
			List<Integer> temp = new ArrayList<>();
			temp.add(candidates.get(i));
			dfs(candidates.get(i), i, target, temp);
		}
		return result;
	}

	private void dfs(int sum, int start, int target, List<Integer> path) {
		if (sum > target) return;
		if (sum == target) {
			result.add(path);
			return;
		}

		for (int i = start; i < candidates.size(); i++) {
			path.add(candidates.get(i));
			dfs(sum + candidates.get(i), i, target, new ArrayList<>(path));
		}
	}
}
