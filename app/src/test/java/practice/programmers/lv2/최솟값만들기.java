package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class 최솟값만들기 {
	@Test
	void test() {
		int solution = solution(new int[]{1, 4, 2}, new int[]{5, 4, 4});
		Assertions.assertEquals(solution, 29);
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/12941
	 * [분류] 정렬
	 *
	 * @param A
	 * @param B
	 * @return A, B 배열에서 각자 하나씩 선정하여 곱한 다음, 모두 더하여 최소값을 구하라.
	 */
	public int solution(int[] A, int[] B) {
		int answer = 0;
		Arrays.sort(A);
		Arrays.sort(B);

		int bIdx = B.length - 1;
		for (int a : A) {
			answer += a * B[bIdx--];
		}
		return answer;
	}
}
