package practice.programmers.summer_winter_2018;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class 스킬트리 {
    public int solution(String skill, String[] skillTrees) {
        int answer = 0;
        Map<Character, Integer> skillMap = init(skill);
        for (String skillTree : skillTrees) {
            if (isCorrect(skillMap, skillTree)) answer++;
        }
        return answer;
    }

    private Map<Character, Integer> init(String skill) {
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < skill.length(); i++) {
            result.put(skill.charAt(i), i);
        }
        return result;
    }

    private boolean isCorrect(Map<Character, Integer> skillMap, String skillTree) {
        int currentSkillNumber = 0;

        for (char skill : skillTree.toCharArray()) {
            if (!skillMap.containsKey(skill)) continue;
            int skillNumber = skillMap.get(skill);
            if (skillNumber > currentSkillNumber) return false;
            else if (skillNumber == currentSkillNumber) currentSkillNumber++;
        }
        return true;
    }
}
