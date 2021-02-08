package practice;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();

		Scanner sc = new Scanner(System.in);
		int testCaseCount = Integer.parseInt(sc.nextLine());

		for (int i = 0; i < testCaseCount; i++) {
			int n = Integer.parseInt(sc.nextLine());
			String[] spl = sc.nextLine().split(" ");
			int[] numbers = new int[spl.length];
			for (int j = 0; j < spl.length; j++) {
				numbers[j] = Integer.parseInt(spl[j]);
			}
			System.out.println(main.solution(n, numbers));
		}
	}

	int[] graph;
	boolean[] visited;
	public int solution(int n, int[] numbers) {
		int result = 0;
		this.graph = new int[n + 1];
		for (int i = 0; i < n; i++) {
			int number = numbers[i];
			this.graph[i + 1] = number;
		}
		this.visited = new boolean[n + 1];

		for (int num = 1; num <= n; num++) {
			if (visited[num]) continue;
			result++;
			dfs(num);
		}
		return result;
	}

	private void dfs(int num) {
		if (visited[num] || graph[num] == 0) return;

		visited[num] = true;
		dfs(graph[num]);
	}
}