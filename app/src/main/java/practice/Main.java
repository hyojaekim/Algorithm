package practice;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();

		Scanner sc = new Scanner(System.in);
		String[] line = sc.nextLine().split(" ");
		int A = Integer.parseInt(line[0]);
		int P = Integer.parseInt(line[1]);
		main.solution(A, P);
	}

	int[] nums;
	Map<Integer, Integer> numbers;
	public void solution(int A, int P) {
		this.nums = init(P);
		this.numbers = new HashMap<>();
		this.numbers.put(A, 1);
		dfs(calculate(A));

		//횟수가 1회인 숫자들만 카운트
		long result = numbers.keySet()
						.stream()
						.filter(key -> numbers.get(key) == 1)
						.count();
		System.out.println(result);
	}

	private void dfs(int number) {
		//방문 횟수가 3회이면 중단
		if (numbers.containsKey(number) && numbers.get(number) >= 3) {
			return;
		}

		//카운
		if (!numbers.containsKey(number)) numbers.put(number, 1);
		else numbers.put(number, numbers.get(number) + 1);

		//number를 계산해서 dfs 돌리기
		dfs(calculate(number));
	}

	private int calculate(int number) {
		int sum = 0;
		for (char num : String.valueOf(number).toCharArray()) {
			sum += nums[Character.getNumericValue(num)];
		}
		return sum;
	}

	private int[] init(int p) {
		int[] result = new int[10];
		for (int num = 0; num < 10; num++) {
			result[num] = (int) Math.pow(num, p);
		}
		return result;
	}
}