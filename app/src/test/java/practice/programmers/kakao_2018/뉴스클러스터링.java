package practice.programmers.kakao_2018;

import java.util.HashMap;
import java.util.Map;

public class 뉴스클러스터링 {
    int solution(String str1, String str2) {
        Map<String, Integer> firstWord = initStr(str1);
        Map<String, Integer> secondWord = initStr(str2);

        if (firstWord.keySet().size() == 0 && secondWord.keySet().size() == 0) {
            return 65536;
        }

        Map<String, Integer> gyo = new HashMap<>();
        Map<String, Integer> hap = new HashMap<>();
        for (String firstKey : firstWord.keySet()) {
            int firstValue = firstWord.get(firstKey);
            setHap(hap, firstKey, firstValue);
            for (String secondKey : secondWord.keySet()) {
                int secondValue = secondWord.get(secondKey);
                setHap(hap, secondKey, secondValue);
                if (!firstKey.equals(secondKey)) continue;
                int min = Math.min(firstValue, secondValue);
                gyo.put(firstKey, min);
                break;
            }
        }
        int resultGyo = 0, resultHap = 0;
        for (Integer value : gyo.values()) {
            resultGyo += value;
        }
        for (Integer value : hap.values()) {
            resultHap += value;
        }
        double result = ((double) resultGyo / (double) resultHap) * (double) 65536;
        return (int) result;
    }

    private void setHap(Map<String, Integer> hap, String firstKey, int firstValue) {
        if (hap.containsKey(firstKey)) {
            hap.put(firstKey, Math.max(hap.get(firstKey), firstValue));
        } else {
            hap.put(firstKey, firstValue);
        }
    }

    private Map<String, Integer> initStr(String str) {
        Map<String, Integer> result = new HashMap<>();
        str = str.toLowerCase();
        for (int i = 0; i < str.length() - 1; i++) {
            String temp = str.substring(i, i + 2);
            if (!isCorrect(temp)) continue;
            if (result.containsKey(temp)) {
                result.put(temp, result.get(temp) + 1);
            } else {
                result.put(temp, 1);
            }
        }
        return result;
    }

    private boolean isCorrect(String temp) {
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) < 'a' || temp.charAt(i) > 'z') return false;
        }
        return true;
    }
}
