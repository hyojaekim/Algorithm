package practice.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 다트게임 {

    Map<Character, Integer> bonusNumber;
    Map<Character, Integer> options;

    public int solution(String dartResult) {
        initBonusAndOptions();
        List<Round> totalRound = init(dartResult);
        for (int i = 1; i < 3; i++) {
            if (totalRound.get(i).isStar()) {
                totalRound.get(i - 1).applyDouble();
            }
        }
        return totalRound.stream().mapToInt(r -> r.point).sum();
    }

    private void initBonusAndOptions() {
        this.bonusNumber = new HashMap<>();
        this.bonusNumber.put('S', 1); this.bonusNumber.put('D', 2); this.bonusNumber.put('T', 3);

        this.options = new HashMap<>();
        this.options.put('x', 1); this.options.put('*', 2); this.options.put('#', -1);
    }

    private List<Round> init(String dartResult) {
        List<Round> totalRound = new ArrayList<>();
        while (!dartResult.isEmpty()) {
            int number = getNumber(dartResult);
            int bonusNumber = getBonusNumber(dartResult);
            char option = getOption(dartResult);
            int point = calculate(number, bonusNumber, option);

            Round round = new Round(point, option);
            totalRound.add(round);
            dartResult = dartResult.substring(round.length(number));
        }
        return totalRound;
    }

    private int calculate(int number, int bonusNumber, char option) {
        return (int) Math.pow(number, bonusNumber) * options.get(option);
    }

    private int getBonusNumber(String dartResult) {
        int index = 1;
        if (dartResult.charAt(0) == '1' && dartResult.charAt(1) == '0') index = 2;
        return bonusNumber.get(dartResult.charAt(index));
    }

    private int getNumber(String dartResult) {
        if (dartResult.charAt(1) == '0') return 10;
        return dartResult.charAt(0) - '0';
    }

    private char getOption(String dartResult) {
        if (dartResult.length() >= 4 && options.containsKey(dartResult.charAt(3))) return dartResult.charAt(3);
        if (dartResult.length() >= 3 && options.containsKey(dartResult.charAt(2))) return dartResult.charAt(2);
        return 'x';
    }

    class Round {
        int point;
        char option;

        public Round(int point, char option) {
            this.point = point;
            this.option = option;
        }

        public int length(int number) {
            int length = (number == 10) ? 3 : 2;
            if (this.option != 'x') return length + 1;
            return length;
        }

        public boolean isStar() { return this.option == '*'; }

        public void applyDouble() { this.point *= 2; }
    }
}