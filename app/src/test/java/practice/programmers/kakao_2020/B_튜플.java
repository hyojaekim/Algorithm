package practice.programmers.kakao_2020;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class B_튜플 {

    public int[] solution(String s) {
        String[] split = s.split("},\\{");
        List<String> tuples = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String temp = split[i].replace("{", "").replace("}", "");
            tuples.add(temp);
        }
        tuples.sort((o1, o2) -> o2.length() - o1.length());
        Set<Integer> result = new LinkedHashSet<>();
        for (int i = 0; i < tuples.size(); i++) {
            String[] numbers = tuples.get(i).split(",");
            for (String number : numbers) {
                Integer realNumber = Integer.valueOf(number);
                result.add(realNumber);
            }
        }

        int[] answer = new int[result.size()];
        int i = 0;
        for (Integer number : result) {
            answer[i++] = number;
        }
        return answer;
    }
}
