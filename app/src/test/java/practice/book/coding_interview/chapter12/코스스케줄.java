package practice.book.coding_interview.chapter12;

import org.junit.jupiter.api.Test;

import java.util.*;

public class 코스스케줄 {

	@Test
	void test() {
		boolean solution = solution(4, new int[][]{{0, 1}, {0, 2}, {1, 2}});
		System.out.println(solution);
	}

	Map<Integer, List<Integer>> graph;
	Set<Integer> traced;

	public boolean solution(int n, int[][] course) {
		if (course.length == 0) return true;
		this.graph = init(course);
		this.traced = new HashSet<>();

		for (int[] c : course) {
			if (!dfs(c[0])) return false;
		}
		return true;
	}

	private boolean dfs(int start) {
		if (traced.contains(start)) {
			return false;
		}
		if (!graph.containsKey(start)) {
			return true;
		}

		traced.add(start);
		for (Integer end : graph.get(start)) {
			if (!dfs(end)) return false;
		}
		traced.remove(start);
		return true;
	}

	private Map<Integer, List<Integer>> init(int[][] course) {
		Map<Integer, List<Integer>> result = new HashMap<>();
		for (int[] c : course) {
			if (result.containsKey(c[0])) {
				result.get(c[0]).add(c[1]);
			} else {
				List<Integer> temp = new ArrayList<>();
				temp.add(c[1]);
				result.put(c[0], temp);
			}
		}
		return result;
	}
}
