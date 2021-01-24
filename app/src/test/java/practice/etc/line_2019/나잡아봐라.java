package practice.etc.line_2019;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 나잡아봐라 {

	@Test
	void test() {
		int solution = solution(11, 2);
		Assertions.assertEquals(solution, 5);
	}

	public int solution(int cony, int brown) {
		if (cony == brown) return 0;
		int[] dist = fill();
		Queue<Integer> q = new LinkedList<>();
		q.offer(brown);
		dist[brown] = 0;

		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int nxt : new int[]{cur - 1, cur + 1, cur * 2}) {
				if (nxt < 0 || nxt > 200000) continue;
				if (dist[nxt] != -1) continue;
				dist[nxt] = dist[cur] + 1;
				q.offer(nxt);
			}
		}

		int time = 1;
		while (cony < 200001) {
			cony += time;
			if (dist[cony] == time) break;
			time++;
		}
		return time;
	}

	private int[] fill() {
		int[] result = new int[200002];
		Arrays.fill(result, -1);
		return result;
	}
}
