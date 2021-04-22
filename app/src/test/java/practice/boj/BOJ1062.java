package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BOJ1062 {

	private List<Set<Character>> getContents(String... temp) {
		List<Set<Character>> contents = new ArrayList<>();
		for (String s : temp) {
			Set<Character> set = new HashSet<>();
			for (char c : s.toCharArray()) {
				set.add(c);
			}
			contents.add(set);
		}
		return contents;
	}

	@Test
	void test() {
		List<Set<Character>> contents = getContents("antarctica", "antahellotica", "antacartica");
		int solution = solution(6, contents);

		Assertions.assertEquals(solution, 2);
	}

	@Test
	void test2() {
		List<Set<Character>> contents = getContents("antaxxxxxxxtica", "antarctica");
		int solution = solution(3, contents);

		Assertions.assertEquals(solution, 0);
	}

	@Test
	void test3() {
		List<Set<Character>> contents = getContents(
						"antabtica",
						"antaxtica",
						"antadtica",
						"antaetica",
						"antaftica",
						"antagtica",
						"antahtica",
						"antajtica",
						"antaktica"
		);
		int solution = solution(8, contents);

		Assertions.assertEquals(solution, 3);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int n = Integer.parseInt(split[0]);
		int k = Integer.parseInt(split[1]);

		List<Set<Character>> contents = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Set<Character> set = new HashSet<>();
			for (char c : br.readLine().toCharArray()) {
				set.add(c);
			}
			contents.add(set);
		}

		BOJ1062 main = new BOJ1062();
		int solution = main.solution(k, contents);
		System.out.println(solution);
	}

	int result;
	List<Set<Character>> contents;
	public int solution(int k, List<Set<Character>> contents) {
		this.contents = contents;
		this.result = 0;

		char[] chars = initTotalChars(contents);
		int n = chars.length;
		k = k - 5;
		if (k < 0) return 0;
		if (n < k) k = n;

		backtracking(n, k, new boolean[n], chars, new HashSet<>(), 0);
		return this.result;
	}

	private void backtracking(int n, int k, boolean[] visited, char[] chars, Set<Character> current, int start) {
		if (current.size() == k) {
			int count = 0;
			for (Set<Character> content : contents) {
				if (possible(content, current)) count++;
			}
			this.result = Math.max(this.result, count);
			return;
		}

		for (int i = start; i < n; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			current.add(chars[i]);
			backtracking(n, k, visited, chars, current, i + 1);
			current.remove(chars[i]);
			visited[i] = false;
		}
	}

	private boolean possible(Set<Character> content, Set<Character> current) {
		if (content.size() > current.size()) return false;
		for (Character c : content) {
			if (!current.contains(c)) return false;
		}
		return true;
	}

	private char[] initTotalChars(List<Set<Character>> contents) {
		Set<Character> result = new HashSet<>();
		for (Set<Character> content : contents) {
			content.remove('a');
			content.remove('n');
			content.remove('t');
			content.remove('i');
			content.remove('c');
			result.addAll(content);
		}
		return initChars(result);
	}

	private char[] initChars(Set<Character> totalChars) {
		char[] result = new char[totalChars.size()];
		int i = 0;
		for (Character c : totalChars) {
			result[i++] = c;
		}
		return result;
	}
}
