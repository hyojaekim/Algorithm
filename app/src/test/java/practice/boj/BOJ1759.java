package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1759 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int r = Integer.parseInt(split[0]);
		int n = Integer.parseInt(split[1]);

		String[] line = br.readLine().split(" ");
		char[] chars = new char[n];
		for (int i = 0; i < n; i++) {
			chars[i] = line[i].charAt(0);
		}
		BOJ1759 main = new BOJ1759();
		List<String> solution = main.solution(n, r, chars);
		for (String s : solution) {
			System.out.println(s);
		}
	}
	private static final Set<Character> vowels;

	static {
		vowels = new HashSet<>();
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
	}

	List<String> result;
	boolean[] visited;

	public List<String> solution(int n, int r, char[] chars) {
		this.result = new ArrayList<>();
		this.visited = new boolean[n];
		Arrays.sort(chars); //문자열 정렬

		backtracking(n, r, chars, 0, new LinkedList<>());

		Collections.sort(result);
		return result;
	}

	private void backtracking(int n, int r, char[] chars, int start, LinkedList<Character> state) {
		if (state.size() == r) {
			int vowelCount = 0;
			StringBuilder br = new StringBuilder();
			for (Character c : state) {
				if (vowels.contains(c)) vowelCount++;
				br.append(c);
			}
			if (vowelCount >= 1 && state.size() - vowelCount >= 2) { //모음 a e i o u 중에 최소 1개 && 자음 최소 2개
				this.result.add(br.toString());
			}
			return;
		}

		for (int i = start; i < n; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			state.add(chars[i]);
			backtracking(n, r, chars, i + 1, state);
			state.removeLast();
			visited[i] = false;
		}
	}

	@Test
	void test() {
		List<String> solution = solution(6, 4, new char[]{'a', 't', 'c', 'i', 's', 'w' });

		Assertions.assertEquals(solution, Arrays.asList(
						"acis",
						"acit",
						"aciw",
						"acst",
						"acsw",
						"actw",
						"aist",
						"aisw",
						"aitw",
						"astw",
						"cist",
						"cisw",
						"citw",
						"istw"
		));
	}
}
