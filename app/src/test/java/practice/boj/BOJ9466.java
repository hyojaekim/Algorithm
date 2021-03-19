package practice.boj;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9466 {

	@Test
	void test() throws IOException {
//		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCount = Integer.parseInt(br.readLine());
		int[] result = new int[testCount];
		for (int i = 0; i < testCount; i++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] requestInfo = new int[n];
			for (int j = 0; j < n; j++) {
				requestInfo[j] = Integer.parseInt(st.nextToken()) - 1;
			}
			result[i] = getProjectCount(n, requestInfo);
		}

		for (int num : result) {
			System.out.println(num);
		}
	}

	/**
	 * [문제] https://www.acmicpc.net/problem/9466
	 * [분류] 그래프, DFS
	 *
	 * @param n 친구수
	 * @param requestInfo
	 * @return 친구 관계를 맺지 못하는 사람의 수 반환
	 */
	private int getProjectCount(int n, int[] requestInfo) {
		int[] check = new int[n];
		int[] start = new int[n];
		int result = 0;
		for (int i = 0; i < n; i++) {
			if (check[i] != 0) continue;
			result += checkCycle(check, i, i, 1, requestInfo, start);
		}
		return n - result;
	}

	private int checkCycle(int[] check, int origin, int cur, int cnt, int[] requestInfo, int[] start) {
		if (check[cur] != 0) {
			return origin != start[cur] ? 0 : cnt - check[cur];
		} else {
			check[cur] = cnt;
			start[cur] = origin;
			return checkCycle(check, origin, requestInfo[cur], cnt + 1, requestInfo, start);
		}
	}
}
