package practice.book.coding_interview.chapter12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class 순열 {

	@Test
	void test() {
		List<List<Integer>> result = solution(Arrays.asList(1, 2, 3));
		Assertions.assertEquals(result.size(), 6);
	}

	/**
	 * 사용한 알고리즘 : 백트래킹(DFS)
	 * 예시 : {{1, 2, 3}, {2, 1, 3} ...}
	 * 1. 방문 o, x를 반복하며 dfs
	 * 3. Q 숫자의 개수가 요청하는 숫자 개수랑 같으면 결과 저장
	 * @param numbers 서로 다른 정수
	 * @return 가능한 모든 순열
	 */
	boolean[] visited;
	List<List<Integer>> result;
	public List<List<Integer>> solution(List<Integer> numbers) {
		this.result = new ArrayList<>();
		this.visited = new boolean[numbers.size()];
		dfs(new LinkedList<>(), numbers);
		return result;
	}

	private void dfs(Queue<Integer> q, List<Integer> numbers) {
		if (q.size() == numbers.size()) {
			result.add(new ArrayList<>(q));
			return;
		}

		for (int i = 0; i < numbers.size(); i++) {
			if (visited[i]) continue;
			visited[i] = true;
			q.add(numbers.get(i));
			dfs(q, numbers);
			q.remove(numbers.get(i));
			visited[i] = false;
		}
	}
}
