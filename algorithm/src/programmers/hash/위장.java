package programmers.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 위장 {

    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, List<String>> convertedClothes = init(clothes);
        for (String key : convertedClothes.keySet()) {
            answer *= convertedClothes.get(key).size() + 1;
        }
        return answer - 1;
    }

    private Map<String, List<String>> init(String[][] clothes) {
        Map<String, List<String>> result = new HashMap<>();
        for (String[] clothe : clothes) {
            result.computeIfAbsent(clothe[1], (key) -> new ArrayList<>()).add(clothe[0]);
        }
        return result;
    }
}
