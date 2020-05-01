package programmers.완전탐색;

public class 숫자야구 {

    public int solution(int[][] baseball) {
        int answer = 0;
        for (int number = 123; number <= 987; number++) {
            int[] numbers = splitNumber(number);
            boolean isPossibleNumber = numbers[0] != numbers[1]
                    && numbers[0] != numbers[2]
                    && numbers[1] != numbers[2]
                    && numbers[0] != 0 && numbers[1] != 0 && numbers[2] != 0;

            if (isPossibleNumber) {
                for (int i = 0; i < baseball.length; i++) {
                    int[] requestNumbers = splitNumber(baseball[i][0]);
                    int strike = findStrikeCount(numbers, requestNumbers);
                    int ball = findBallCount(numbers, requestNumbers);
                    if (strike != baseball[i][1] || ball != baseball[i][2]) {
                        isPossibleNumber = false;
                        break;
                    }
                }
            }
            if (isPossibleNumber) {
                answer++;
            }
        }
        return answer;
    }

    private int findBallCount(int[] numbers, int[] requestNumbers) {
        int ball = 0;
        if (requestNumbers[0] == numbers[1] || requestNumbers[0] == numbers[2]) {
            ball++;
        }
        if (requestNumbers[1] == numbers[0] || requestNumbers[1] == numbers[2]) {
            ball++;
        }
        if (requestNumbers[2] == numbers[0] || requestNumbers[2] == numbers[1]) {
            ball++;
        }
        return ball;
    }

    private int findStrikeCount(int[] numbers, int[] requestNumbers) {
        int strike = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == requestNumbers[i]) {
                strike++;
            }
        }
        return strike;
    }

    public int[] splitNumber(int number) {
        int firstNumber = number / 100;
        int secondNumber = (number - (firstNumber * 100)) / 10;
        int thirdNumber = (number - ((firstNumber * 100) + (secondNumber * 10)));
        return new int[]{firstNumber, secondNumber, thirdNumber};
    }
}
