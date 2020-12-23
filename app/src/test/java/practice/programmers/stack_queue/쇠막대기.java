package practice.programmers.stack_queue;

import java.util.Stack;

public class 쇠막대기 {

    public int solution(String arrangement) {
        int answer = 0;
        char[] chars = arrangement.replace("()", "0").toCharArray();
        Stack<Character> sticks = new Stack<>();

        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            if (temp == '(') {
                sticks.push(temp);
            } else if (temp == ')') {
                sticks.pop();
                answer++;
            } else if (temp == '0') {
                answer = answer + sticks.size();
            }
        }
        return answer;
    }

    public int solution2(String arrangement) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arrangement.length(); i++) {
            char temp = arrangement.charAt(i);

            if (temp == '(') {
                if (i + 1 != arrangement.length() && arrangement.charAt(i + 1) == ')') {
                    answer += stack.size();
                    i++;
                } else {
                    stack.push(i);
                }
            } else if (temp == ')') {
                stack.pop();
                answer++;
            }
        }
        return answer;
    }
}
