package practice.boj;

import java.util.Arrays;
import java.util.Scanner;

class BOJ2839 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int result = solution(num);
		System.out.println(result);
	}

	public static int solution(int n) {
		int[] dp = new int[5001];
		int max = 100_000_000;
		Arrays.fill(dp, max);
		dp[3] = 1;
		dp[5] = 1;

		for (int i = 6; i <= n; i++) {
			dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
		}

		return dp[n] >=  max ? -1 : dp[n];
	}
}
