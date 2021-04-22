package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1339 {

	@Test
	void test() {
		int solution = solution(2, new String[]{"AAA", "AAA"}, new char[]{'A'});

		Assertions.assertEquals(solution, 1998);
	}

	@Test
	void test2() {
		int solution = solution(2, new String[]{"GCF", "ACDEB"}, new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G' });

		Assertions.assertEquals(solution, 99437);
	}

	@Test
	void test3() {
		int solution = solution(10, new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",}, new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',});

		Assertions.assertEquals(solution, 45);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Set<Character> set = new HashSet<>();
		String[] contents = new String[n];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			contents[i] = str;
			for (char c : str.toCharArray()) {
				set.add(c);
			}
		}
		char[] chars = new char[set.size()];
		int i = 0;
		for (Character c : set) {
			chars[i++] = c;
		}

		BOJ1339 main = new BOJ1339();
		int solution = main.solution(n, contents, chars);
		System.out.println(solution);
	}

	int result;

	public int solution(int n, String[] contents, char[] chars) {
		this.result = 0;
		backtracking(chars.length, chars, new boolean[chars.length], new ArrayList<>(), contents);
		return result;
	}

	private void backtracking(int n, char[] chars, boolean[] visited, List<Character> numbers, String[] contents) {
		if (numbers.size() == n) {
			Map<Character, Integer> map = initMap(numbers);
			int sum = 0;
			for (String content : contents) {
				int size = content.length();
				int num = 1, result = 0;
				for (int i = size - 1; i >= 0; i--) {
					result += (map.get(content.charAt(i)) * num);
					num *= 10;
				}
				sum += result;
			}
			if (this.result < sum) this.result = sum;
			return;
		}

		for (int i = 0; i < n; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			numbers.add(chars[i]);
			backtracking(n, chars, visited, numbers, contents);
			numbers.remove(numbers.size() - 1);
			visited[i] = false;
		}
	}

	private Map<Character, Integer> initMap(List<Character> numbers) {
		Map<Character, Integer> result = new HashMap<>();
		int num = 9;
		for (Character c : numbers) {
			result.put(c, num--);
		}
		return result;
	}
}
