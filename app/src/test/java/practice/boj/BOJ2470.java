package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2470 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] numbers = new int[n];
		String[] split = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(split[i]);
		}
		Arrays.sort(numbers);

		int min = 0, max = n - 1, result = Integer.MAX_VALUE, start = 0, end = n - 1;

		while (start < end) {
			int tempResult = numbers[start] + numbers[end];
			if (result > Math.abs(tempResult)) {
				result = Math.abs(tempResult);
				min = numbers[start];
				max = numbers[end];
				if (result == 0) break;
			}
			if (tempResult > 0) end--;
			else if (tempResult < 0) start++;
		}
		System.out.println(min + " " + max);
	}
}
