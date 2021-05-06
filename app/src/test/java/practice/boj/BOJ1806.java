package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1806 {

	/**
	 * 연속된 수들의 부분합 -> S 이상이 되는 것중 가장 짧은것 -> 길이(못구하면 0)
	 * 100,000 * 10,000 -> 10억 -> int로 가능
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int n = Integer.parseInt(split[0]);
		int s = Integer.parseInt(split[1]);
		int[] numbers = new int[n];
		split = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(split[i]);
		}

		int start, end = 0, sum = 0, result = Integer.MAX_VALUE;
		for (start = 0; start < n; start++) {
			while (end < n && sum < s) {
				sum += numbers[end];
				end++;
			}
			if (sum >= s && result > end - start) {
				result = end - start;
			}
			sum -= numbers[start];
		}
		System.out.println(result == Integer.MAX_VALUE ? 0 : result);
	}
}
