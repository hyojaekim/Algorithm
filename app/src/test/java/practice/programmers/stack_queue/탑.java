package practice.programmers.stack_queue;

public class 탑 {
    public int[] solution(int[] heights) {
        int[] answer = new int[heights.length];
        answer[0] = 0;
        for (int i = heights.length - 1; i > 0; i--) {
            boolean isContinue = false;
            for (int j = i - 1; j >= 0; j--) {
                if (heights[i] < heights[j]) {
                    answer[i] = j + 1;
                    isContinue = true;
                    break;
                }
            }
            if (!isContinue) {
                answer[i] = 0;
            }
        }
        return answer;
    }
}
