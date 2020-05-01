package programmers.완전탐색;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 소수찾기 {

    public int solution(String number) {
        int maxNumber = findMaxNumber(number.toCharArray());
        boolean[] decimals = findDecimals(maxNumber);
        return matchCount(maxNumber, decimals);
    }

    private int findMaxNumber(char[] numbers) {
        Arrays.sort(numbers);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = numbers.length - 1; i >= 0; i--) {
            stringBuilder.append(numbers[i]);
        }
        return Integer.parseInt(stringBuilder.toString());
    }

    private boolean[] findDecimals(int maxNumber) {
        boolean[] result = new boolean[maxNumber + 1];
        for (int i = 2; i < result.length; i++) {
            if (!result[i]) {
                int tempIndex = 1;
                for (int j = i * 2; j < result.length; j = i * tempIndex) {
                    result[j] = true;
                    tempIndex++;
                }
            }
        }
        return result;
    }

    private int matchCount(int maxNumber, boolean[] decimals) {
        int count = 0;
        for (int primeNumber = 2; primeNumber < decimals.length; primeNumber++) {
            List<Character> convertMaxNumber = convertNumbers(maxNumber);
            boolean isMatch = true;
            if (!decimals[primeNumber]) {
                List<Character> decimal = convertNumbers(primeNumber);
                for (Character decimalPiece : decimal) {
                    if (!convertMaxNumber.contains(decimalPiece)) {
                        isMatch = false;
                        break;
                    }
                    convertMaxNumber.remove(decimalPiece);
                }
                if (isMatch) {
                    count++;
                }
            }
        }
        return count;
    }

    private List<Character> convertNumbers(int maxNumber) {
        List<Character> convertMaxNumber = new ArrayList<>();
        for (char number : String.valueOf(maxNumber).toCharArray()) {
            convertMaxNumber.add(number);
        }
        return convertMaxNumber;
    }
}
