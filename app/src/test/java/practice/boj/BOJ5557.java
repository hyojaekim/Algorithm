package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5557 {

	@Test
	void test() {
		long solution = solution(11, new int[]{8, 3, 2, 4, 8, 7, 2, 4, 0, 8, 8});

		Assertions.assertEquals(solution, 10);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(s[i]);
		}

		BOJ5557 main = new BOJ5557();
		long solution = main.solution(n, numbers);
		System.out.println(solution);
	}

	public long solution(int n, int[] numbers) {
		long[][] dp = new long[n][21];
		dp[0][numbers[0]] = 1;

		int length = numbers.length - 1;
		int target = numbers[length];
		for (int i = 1; i < length; i++) {
			for (int k = 0; k < 21; k++) {
				if (dp[i - 1][k] == 0) continue;
				int sum = k + numbers[i];
				int subtract = k - numbers[i];
				if (sum <= 20) dp[i][sum] += dp[i - 1][k];
				if (subtract >= 0) dp[i][subtract] += dp[i - 1][k];
			}
		}
		return dp[length - 1][target] == 0 ? -1 : dp[length - 1][target];
	}
}
