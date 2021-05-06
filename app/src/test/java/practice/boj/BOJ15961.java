package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ15961 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int n = Integer.parseInt(split[0]); //초밥 개수
		int d = Integer.parseInt(split[1]); //초밥 가지수
		int k = Integer.parseInt(split[2]); //연속해서 먹는 접시 수
		int c = Integer.parseInt(split[3]); //쿠폰 번호

		int[] totalSushi = new int[n];
		Map<Integer, Integer> counter = new HashMap<>();
		for (int i = 0; i < n; i++) {
			totalSushi[i] = Integer.parseInt(br.readLine());
			if (i < k) {
				if (!counter.containsKey(totalSushi[i])) counter.put(totalSushi[i], 1);
				else counter.put(totalSushi[i], counter.get(totalSushi[i]) + 1);
			}
		}
		int result = !counter.containsKey(c) ? counter.size() + 1 : counter.size();
		result = solution(counter, totalSushi, result, n, k, c);
		System.out.println(result);
	}

	private static int solution(Map<Integer, Integer> counter, int[] totalSushi, int result, int n, int k, int c) {
		int end = k;
		for (int start = 1; start < n; start++) {
			remove(counter, totalSushi[start - 1]);
			add(counter, totalSushi[end]);
			int size = !counter.containsKey(c) ? counter.size() + 1 : counter.size();
			result = Math.max(result, size);
			end = (end + 1 == n) ? 0 : end + 1;
		}
		return result;
	}

	private static void remove(Map<Integer, Integer> counter, int target) {
		if (counter.get(target) == 1) {
			counter.remove(target);
			return;
		}
		counter.put(target, counter.get(target) - 1);
	}

	private static void add(Map<Integer, Integer> counter, int target) {
		if (!counter.containsKey(target)) {
			counter.put(target, 1);
			return;
		}
		counter.put(target, counter.get(target) + 1);
	}
}
