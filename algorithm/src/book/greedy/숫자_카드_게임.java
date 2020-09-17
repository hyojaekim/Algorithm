package book.greedy;

import java.util.Arrays;

public class 숫자_카드_게임 {

    public int solution(int N, int M, int[][] cards) {
        int answer = 0;
        for (int[] card : cards) {
            Arrays.sort(card);
            answer = Math.max(answer, card[0]);
        }
        return answer;
    }
}
