package practice.boj;

import org.junit.jupiter.api.Test;

public class BOJ17626 {

	@Test
	void test() {
		solution(11339);
	}

	public void solution(int num) {
		int count = 0;
		while (num > 1) {
			num = (int) Math.sqrt(num);
			count++;
		}
		System.out.println(count);
	}
}
