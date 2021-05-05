package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11659 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] split = br.readLine().split(" ");
		int n = Integer.parseInt(split[0]);
		int m = Integer.parseInt(split[1]);
		int[] numbers = new int[n];
		split = br.readLine().split(" ");
		numbers[0] = Integer.parseInt(split[0]);
		for (int i = 1; i < n; i++) {
			numbers[i] = Integer.parseInt(split[i]) + numbers[i - 1];
		}

		for (int k = 0; k < m; k++) {
			split = br.readLine().split(" ");
			int i = Integer.parseInt(split[0]) - 1;
			int j = Integer.parseInt(split[1]) - 1;

			int before = i == 0 ? 0 : numbers[i - 1];
			System.out.println(numbers[j] - before);
		}
	}
}
