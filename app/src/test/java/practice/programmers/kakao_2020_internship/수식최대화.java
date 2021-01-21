package practice.programmers.kakao_2020_internship;

import org.junit.jupiter.api.Test;

import java.util.*;

public class 수식최대화 {

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
		long result = 0;
		Deque<Long> numberQ = getNumberQ(expression);
		Deque<Character> operatorQ = getOperatorQ(expression);
		Set<String> totalOperators = init(operatorQ);
		for (String symbols : totalOperators) {
			result = Math.max(result, calculate(new LinkedList<>(numberQ), new LinkedList<>(operatorQ), symbols));
		}
		return result;
	}

	private Deque<Long> getNumberQ(String expression) {
		Deque<Long> result = new LinkedList<>();
		for (String s : expression.split("[-,*,+]+")) {
			if (s.isEmpty()) continue;
			result.add(Long.parseLong(s));
		}
		return result;
	}

	private Deque<Character> getOperatorQ(String expression) {
		Deque<Character> result = new LinkedList<>();
		for (String s : expression.split("[0-9]+")) {
			if (s.isEmpty()) continue;
			result.add(s.charAt(0));
		}
		return result;
	}

	private Set<String> init(Deque<Character> operatorQ) {
		List<String> operations = Arrays.asList("+-*", "+*-", "-+*", "-*+", "*+-", "*-+");
		Set<Character> sets = new HashSet<>(operatorQ);
		if (sets.size() == 3) return new HashSet<>(operations);
		Set<String> result = new HashSet<>();
		for (String operation : operations) {
			StringBuilder sb = new StringBuilder();
			for (char c : operation.toCharArray()) {
				if (!sets.contains(c)) continue;
				sb.append(c);
			}
			result.add(sb.toString());
		}
		return result;
	}

	private long calculate(Deque<Long> numberQ, Deque<Character> operatorQ, String symbols) {
		for (char symbol : symbols.toCharArray()) {
			int cnt = operatorQ.size();
			while (cnt > 0) {
				char curSymbol = operatorQ.poll();
				long curNumber = numberQ.poll();
				if (curSymbol == symbol) {
					long nextNumber = numberQ.poll();
					long result = calculate(curNumber, nextNumber, symbol);
					numberQ.offerFirst(result);
				} else {
					numberQ.offerLast(curNumber);
					operatorQ.offerLast(curSymbol);
				}
				cnt--;
			}
			numberQ.offerLast(numberQ.poll());
		}
		return Math.abs(numberQ.poll());
	}

	private long calculate(long a, long b, char symbol) {
		if (symbol == '*') {
			return a * b;
		} else if (symbol == '-') {
			return a - b;
		} else {
			return a + b;
		}
	}
}
