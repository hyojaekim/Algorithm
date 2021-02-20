package practice.programmers.lv3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class NQueen {
	@Test
	void test() {
		int solution = solution(4);
		Assertions.assertEquals(solution, 2);
	}
	int answer;
	int max;
	boolean[] visited;
	int[] dx = {1, -1, 1, -1};
	int[] dy = {1, -1, -1, 1};
	public int solution(int n) {
		this.answer = 0;
		this.max = n;
		this.visited = new boolean[this.max];
		Set<Pair> numbers = new LinkedHashSet<>();
		dfs(numbers, 0);
		return answer;
	}

	private void dfs(Set<Pair> numbers, int y) {
		if (numbers.size() == max) {
			for (Pair pair : numbers) {
				int nx, ny;
				for (int i = 0; i < 4; i++) {
					nx = pair.x;
					ny = pair.y;
					while (!(nx < 0 || ny < 0 || nx >= max || ny >= max)) {
						nx += dx[i];
						ny += dy[i];
						if (numbers.contains(new Pair(nx, ny))) return;
					}
				}
			}
			this.answer++;
			return;
		}

		for (int num = 0; num < max; num++) {
			if (visited[num]) continue;
			visited[num] = true;
			Pair pair = new Pair(num, y);
			numbers.add(pair);
			dfs(numbers, y + 1);
			visited[num] = false;
			numbers.remove(pair);
		}
	}

	static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Pair pair = (Pair) o;
			return x == pair.x && y == pair.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
}
