package programmers.kakao_2020_internship;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 키패드_누르기 {

    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        boolean isRight = hand.equals("right");
        numbers = init(numbers);
        Set<Integer> leftNumbers = new HashSet<>(Arrays.asList(1, 4, 7));
        Set<Integer> rightNumbers = new HashSet<>(Arrays.asList(3, 6, 9));
        int currentLeftHand = 10;
        int currentRightHand = 12;

        for (int number : numbers) {
            boolean right;
            if (leftNumbers.contains(number)) {
                right = false;
            } else if (rightNumbers.contains(number)) {
                right = true;
            } else {
                int rightPoint = cal(number, currentRightHand);
                int leftPoint = cal(number, currentLeftHand);
                if (rightPoint == leftPoint) {
                    right = isRight;
                } else {
                    right = rightPoint < leftPoint;
                }
            }

            if (right) {
                currentRightHand = number;
                answer.append("R");
            } else {
                currentLeftHand = number;
                answer.append("L");
            }
        }
        return answer.toString();
    }

    private int[] init(int[] numbers) {
        int[] result = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = numbers[i] == 0 ? 11 : numbers[i];
        }
        return result;
    }

    private int cal(int number, int currentHandNumber) {
        number = number - 1;
        currentHandNumber= currentHandNumber - 1;
        return Math.abs((number / 3) - (currentHandNumber / 3)) + Math.abs((number % 3) - (currentHandNumber % 3));
    }
}
