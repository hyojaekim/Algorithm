package programmers.stack_queue;

public class 주식가격 {

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        int number;
        int count;
        for (int i = 0; i < prices.length; i++) {
            number = prices[i];
            count = 0;
            for (int j = i + 1; j < prices.length; j++) {
                count++;
                if (number > prices[j]) {
                    break;
                }
            }
            answer[i] = count;
        }
        return answer;
    }
}
