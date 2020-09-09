package study;

import java.util.*;

public class 수식_최대화 {

    List<Long> numbers;
    String operators;

    public long solution(String expression) {
        initNumbersAndOperators(expression);
        long answer = 0;
        for (char[] combinationOperators : initCombinationOperators()) {
            List<Long> copyNumbers = new LinkedList<>(this.numbers);
            String copyOperators = this.operators;
            long calculatedResult = calculateTotalNumbers(combinationOperators, copyOperators, copyNumbers);
            answer = Math.max(calculatedResult, answer);
        }
        return answer;
    }

    private void initNumbersAndOperators(String expression) {
        Set<Character> operatorKeys = new HashSet<>(Arrays.asList('*', '+', '-'));
        List<Long> numbers = new ArrayList<>();
        StringBuilder tempNumber = new StringBuilder();
        StringJoiner operatorJoiner = new StringJoiner(",");
        for (char c : expression.toCharArray()) {
            if (operatorKeys.contains(c)) {
                numbers.add(Long.parseLong((tempNumber.toString())));
                tempNumber = new StringBuilder();
                operatorJoiner.add(String.valueOf(c));
            } else {
                tempNumber.append(c);
            }
        }
        numbers.add(Long.parseLong(tempNumber.toString()));
        this.numbers = numbers;
        this.operators = operatorJoiner.toString();
    }

    private char[][] initCombinationOperators() {
        return new char[][]{
                {'*', '+', '-'},
                {'*', '-', '+'},
                {'+', '*', '-'},
                {'+', '-', '*'},
                {'-', '*', '+'},
                {'-', '+', '*'}
        };
    }

    private long calculateTotalNumbers(char[] combinationOperators, String totalOperator, List<Long> numbers) {
        List<String> operators = new LinkedList<>(Arrays.asList(totalOperator.split(",")));

        for (char combinationOperator : combinationOperators) {
            if (operators.size() == 0) break;
            String convertedCombinationOperator = String.valueOf(combinationOperator);
            List<Integer> operatorIndex = new ArrayList<>();
            for (int i = 0; i < operators.size(); i++) {
                String operator = operators.get(i);
                if (!convertedCombinationOperator.equals(operator)) continue;
                long currentNumber = numbers.get(i);
                long nextNumber = numbers.get(i + 1);
                long calculatedResult = calculateTwoNumbers(operator, currentNumber, nextNumber);
                numbers.set(i + 1, calculatedResult);
                operatorIndex.add(i);
            }
            int minusIndex = 0;
            for (int index : operatorIndex) {
                operators.remove(index - minusIndex);
                numbers.remove(index - minusIndex);
                minusIndex++;
            }
        }
        return numbers.size() >= 1 ? Math.abs(numbers.get(0)) : 0;
    }

    private long calculateTwoNumbers(String operation, long firstNumber, long secondNumber) {
        if (operation.equals("*")) return firstNumber * secondNumber;
        else if (operation.equals("-")) return firstNumber - secondNumber;
        else return firstNumber + secondNumber;
    }
}
