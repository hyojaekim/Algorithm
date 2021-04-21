package practice.boj;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ1920 {

	@Test
	void test() {
		solution(5, new int[]{4, 1, 5, 2, 3}, 5, new int[]{1, 3, 7, 9, 5});
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] numbers = new int[n];
		String[] split = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(split[i]);
		}

		int m = Integer.parseInt(br.readLine());
		int[] targetNumbers = new int[m];
		String[] split2 = br.readLine().split(" ");
		for (int i = 0; i < m; i++) {
			targetNumbers[i] = Integer.parseInt(split2[i]);
		}

		solution(n, numbers, m, targetNumbers);
	}

	public static void solution(int n, int[] numbers, int m, int[] targetNumbers) {
		Set<Integer> convertedNumbers = new HashSet<>();
		for (int number : numbers) {
			convertedNumbers.add(number);
		}

		for (int targetNumber : targetNumbers) {
			System.out.println(convertedNumbers.contains(targetNumber) ? 1 : 0);
		}
	}
}
