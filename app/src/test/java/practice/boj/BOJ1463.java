package practice.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1463 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		BOJ1463 main = new BOJ1463();
		System.out.println(main.solution(n));
	}

	public int solution(int n) {
		int[] dp = new int[n + 1];

		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1];
			if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2]);
			if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3]);
			dp[i]++;
		}
		return dp[n];
	}
}
