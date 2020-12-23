package practice.programmers.kakao_2018;

import java.util.ArrayList;
import java.util.List;

public class n진수_게임 {

    public String solution(int n, int t, int m, int p) {
        StringBuilder resultNumber = new StringBuilder();
        int number = 0;
        while (resultNumber.length() <= t * m) {
            resultNumber.append(cal(number, n));
            number++;
        }
        char[] numbers = resultNumber.toString().toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < t; i++) {
            result.append(numbers[(m * i) + (p - 1)]);
        }
        return result.toString();
    }

    private String cal(int number, int n) {
        StringBuilder result = new StringBuilder();
        List<Character> contents = new ArrayList<>();
        while (number / n != 0) {
            int remainder = number % n;
            contents.add(convertNumber(remainder));
            number = number / n;
        }
        contents.add(convertNumber(number));
        for (int i = contents.size() - 1; i >= 0; i--) {
            result.append(contents.get(i));
        }
        return result.toString();
    }

    private char convertNumber(int number) {
        if (number > 9) {
            return (char) ('A' + number - 10);
        } else {
            return (char) (number + '0');
        }
    }
}
