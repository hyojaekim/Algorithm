package practice.programmers.kakao_2020_internship;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class B_수식최대화 {

	@Test
	void test() {
		long result = solution("100-200*300-500+20");
		System.out.println(result);
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/67257
	 * [분류] 구현
	 *
	 * @param expression 수식
	 * @return 연산자 우선순위를 변경하여 최대 값을 구하라.
	 */
	public long solution(String expression) {
		long answer = Long.MIN_VALUE;
		List<Long> numbers = Arrays.stream(expression.split("[-+*]")).map(Long::parseLong).collect(Collectors.toList());
		List<Character> operators = Arrays.stream(expression.split("[0-9]")).filter(s -> !s.isEmpty()).map(s -> s.charAt(0)).collect(Collectors.toList());
		List<String> priorityOperators = Arrays.asList("*+-", "*-+", "+-*", "+*-", "-+*", "-*+");

		for (String priorityOperator : priorityOperators) {
			answer = Math.max(answer, calculate(new ArrayList<>(numbers), new ArrayList<>(operators), priorityOperator));
		}
		return answer;
	}

	private long calculate(List<Long> numbers, List<Character> operators, String priorityOperators) {
		for (char priorityOperator : priorityOperators.toCharArray()) {
			for (int i = 0; i < operators.size(); i++) {
				char operator = operators.get(i);
				if (operator != priorityOperator) continue;

				long calculatedNumber = calculate(operator, numbers.get(i), numbers.get(i + 1));
				numbers.set(i, calculatedNumber);
				numbers.remove(i + 1);
				operators.remove(i);
				i--;
			}
		}
		return Math.abs(numbers.get(0));
	}

	private long calculate(char operator, long firstNumber, long secondNumber) {
		if (operator == '+') return firstNumber + secondNumber;
		else if (operator == '*') return firstNumber * secondNumber;
		else return firstNumber - secondNumber;
	}
}
