package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class BOJ2668 {

	@Test
	void test() {
		int[] solution = solution(7, new int[]{2, 0, 0, 4, 4, 3, 5});
		Assertions.assertArrayEquals(solution, new int[]{3, 1, 3, 5});
	}

	/**
	 * [문제] https://www.acmicpc.net/problem/2668
	 * [분류] 그래프, DFS
	 * [삽질]
	 * - 하나의 긴 사이클만 갱신하도록 구현해서 실패 -> 모든 사이클을 구해서 해당 위치를 구해야 했음
	 *
	 * @param n 1부터 N까지
	 * @param numbers 1부터 N까지 인덱스에 해당하는 숫자들
	 * @return 최대로 많이 뽑는 방법을 반환한다. (0:개수, 1~:뽑은 숫자들)
	 */
	public int[] solution(int n, int[] numbers) {
		Set<Integer> resultNumbers = new HashSet<>();
		for (int i = 0; i < numbers.length; i++) {
			Set<Integer> tempNumbers = new HashSet<>();
			boolean[] visited = new boolean[n];
			visited[i] = true;
			tempNumbers.add(i);

			if (isCycle(i, numbers[i], numbers, tempNumbers, visited)) {
				resultNumbers.addAll(tempNumbers);
			}
		}

		List<Integer> sortedResultNumbers = sort(resultNumbers);
		return getResult(sortedResultNumbers);
	}

	private boolean isCycle(int origin, int cur, int[] numbers, Set<Integer> tempNumbers, boolean[] visited) {
		if (origin == cur) return true;
		if (visited[cur]) return false;

		visited[cur] = true;
		tempNumbers.add(cur);
		return isCycle(origin, numbers[cur], numbers, tempNumbers, visited);
	}

	private List<Integer> sort(Set<Integer> resultNumbers) {
		return new ArrayList<>(resultNumbers)
						.stream()
						.distinct()
						.map(num -> num + 1)
						.sorted(Comparator.comparingInt(o -> o))
						.collect(Collectors.toList());
	}

	private int[] getResult(List<Integer> sortedResultNumbers) {
		int[] result = new int[sortedResultNumbers.size() + 1];
		result[0] = sortedResultNumbers.size();

		for (int i = 0; i < sortedResultNumbers.size(); i++) {
			result[i + 1] = sortedResultNumbers.get(i);
		}
		return result;
	}
}
