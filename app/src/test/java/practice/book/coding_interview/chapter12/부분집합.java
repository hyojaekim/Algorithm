package practice.book.coding_interview.chapter12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class 부분집합 {
	@Test
	void test() {
		List<List<Integer>> result = solution(Arrays.asList(1, 2, 3));
		for (List<Integer> r : result) {
			for (Integer n : r) {
				System.out.print(n + " ");
			}
			System.out.println();
		}
		Assertions.assertEquals(result.size(), 8);
	}

	List<List<Integer>> result;
	List<Integer> numbers;

	/**
	 * 사용 알고리즘 : 조합(dfs)
	 * @param numbers 주어진 숫자
	 * @return 주어진 숫자의 모든 부분 집합을 구하라
	 */
	public List<List<Integer>> solution(List<Integer> numbers) {
		this.result = new ArrayList<>();
		this.numbers = numbers;

		dfs(numbers.size(), 0, new LinkedList<>());
		return result;
	}

	private void dfs(int n, int index, Queue<Integer> q) {
		this.result.add(new LinkedList<>(q));

		for (int i = index; i < n; i++) {
			q.add(numbers.get(i));
			dfs(n, i + 1, q);
			q.remove(numbers.get(i));
		}
	}
}
