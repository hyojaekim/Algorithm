package programmers.greedy;

public class 큰수만들기 {

    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder(number);
        int deleteCount = 0;
        int index = 0;

        while (deleteCount != k) {
            if (index > 0 && answer.charAt(index - 1) < answer.charAt(index)) {
                deleteCount++;
                answer.deleteCharAt(index - 1);
                index--;
            } else if (index + 1 < answer.length() && answer.charAt(index) < answer.charAt(index + 1)) {
                deleteCount++;
                answer.deleteCharAt(index);
            } else if (index + 1 == answer.length()) {
                for (int i = 0; i < k - deleteCount; i++) {
                    answer.deleteCharAt(answer.length() - 1);
                }
                return answer.toString();
            } else {
                index++;
            }
        }
        return answer.toString();
    }
}
