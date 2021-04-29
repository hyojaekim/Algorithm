package practice.boj;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ15663 {

	@Test
	void test() {
		solution(3, 1, new int[]{4, 4, 2});
	}

	@Test
	void test2() {
		solution(4, 2, new int[]{9, 7, 9, 1});
	}

	@Test
	void test3() {
		solution(4, 4, new int[]{1, 1, 1, 1});
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);

		int[] numbers = new int[n];
		String[] split = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(split[i]);
		}

		BOJ15663 main = new BOJ15663();
		main.solution(n, m, numbers);
	}

	Set<String> totalPermutations;
	List<String> result;
	public void solution(int n, int m, int[] numbers) {
		Arrays.sort(numbers);

		this.totalPermutations = new HashSet<>();
		this.result = new ArrayList<>();
		backtracking(new boolean[numbers.length], n, m, numbers, new LinkedList<>());

		for (String s : result) {
			System.out.println(s);
		}
	}

	private void backtracking(boolean[] visited, int n, int m, int[] numbers, LinkedList<Integer> state) {
		if (state.size() == m) {
			StringJoiner sj = new StringJoiner(" ");
			for (Integer num : state) sj.add(num.toString());
			String str = sj.toString();

			if (!totalPermutations.contains(str)) {
				totalPermutations.add(str);
				this.result.add(str);
			}
			return;
		}

		for (int i = 0; i < n; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			state.add(numbers[i]);
			backtracking(visited, n, m, numbers, state);
			state.removeLast();
			visited[i] = false;
		}
	}
}
