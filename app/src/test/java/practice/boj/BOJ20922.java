package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ20922 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int n = Integer.parseInt(split[0]);
		int k = Integer.parseInt(split[1]);
		int[] numbers = new int[n];
		split = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(split[i]);
		}

		int result = solution(n, k, numbers);
		System.out.println(result);
	}

	private static int solution(int n, int k, int[] numbers) {
		int max = 1, start = 0, end = 0;
		Map<Integer, Integer> counter = new HashMap<>();
		counter.put(numbers[start], 1);

		for (start = 0; start < n; start++) {
			while (end + 1 < n) {
				if (!add(counter, numbers, k, ++end)) {
					end--;
					break;
				}
			}
			max = Math.max(max, end - start + 1);
			remove(counter, numbers, start);
		}
		return max;
	}

	private static boolean add(Map<Integer, Integer> counter, int[] numbers, int k, int start) {
		int number = numbers[start];
		boolean result = false;
		if (!counter.containsKey(number)) {
			result = true;
			counter.put(number, 1);
		} else if (counter.get(number) + 1 <= k) {
			result = true;
			counter.put(number, counter.get(number) + 1);
		}
		return result;
	}

	private static void remove(Map<Integer, Integer> counter, int[] numbers, int start) {
		int number = numbers[start];
		counter.put(number, counter.get(number) - 1);
		if (counter.get(number) == 0) {
			counter.remove(number);
		}
	}
}
