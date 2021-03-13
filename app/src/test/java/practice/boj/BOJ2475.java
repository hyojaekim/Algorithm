package practice.boj;

public class BOJ2475 {

	public int solution(int[] numbers) {
		int answer = 0;
		for (int number : numbers) {
			answer += Math.pow(number, 2);
		}
		return answer % 10;
	}
}
