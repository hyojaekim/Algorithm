package practice.boj;

import java.util.Scanner;

public class BOJ2748 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());

		System.out.println(solution(n));
	}

	public static long solution(int n) {
		long[] dp = new long[n + 1];
		dp[1] = 1;

		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}
}
