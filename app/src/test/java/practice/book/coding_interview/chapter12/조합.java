package practice.book.coding_interview.chapter12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 조합 {

	@Test
	void test() {
		List<List<Integer>> solution = solution(4, 2);
		Assertions.assertEquals(solution.size(), 6);
	}

	/**
	 * 1 ~ n까지 수 중에서 k개의 조합을 구하라
	 * ex) n = 4, k = 2 -> {{1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 4}}
	 * @param n 전체 수
	 * @param k k개의 조합
	 * @return 전체수를 받아 k개의 조합을 구하라
	 */
	List<List<Integer>> result;
	int n;
	int k;
	public List<List<Integer>> solution(int n, int k) {
		this.n = n;
		this.k = k;
		this.result = new ArrayList<>();

		dfs(new LinkedList<>(), 1);
		return result;
	}

	private void dfs(Queue<Integer> elements, int start) {
		if (elements.size() == k) {
			result.add(new LinkedList<>(elements));
		}

		for (int num = start; num <= n; num++) {
			elements.add(num);
			dfs(elements, num + 1);
			elements.remove(num);
		}
	}
}
