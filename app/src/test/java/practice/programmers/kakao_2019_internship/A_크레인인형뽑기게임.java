package practice.programmers.kakao_2019_internship;

import java.util.*;

public class A_크레인인형뽑기게임 {

    public int solution(int[][] requestBoard, int[] moves) {
        int answer = 0;
        List<Queue<Integer>> board = init(requestBoard);
        Stack<Integer> basket = new Stack<>();
        for (int i = 0; i < moves.length; i++) {
            int moveIndex = moves[i] - 1;
            Queue<Integer> q = board.get(moveIndex);
            if (q.isEmpty()) continue;
            Integer doll = q.poll();
            if (!basket.isEmpty() && basket.peek().equals(doll)) {
                basket.pop();
                answer += 2;
            } else {
                basket.add(doll);
            }
        }
        return answer;
    }

    private List<Queue<Integer>> init(int[][] board) {
        List<Queue<Integer>> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            Queue<Integer> q = new LinkedList<>();
            for (int j = 0; j < board.length; j++) {
                int doll = board[j][i];
                if (doll != 0) {
                    q.add(board[j][i]);
                }
            }
            result.add(q);
        }
        return result;
    }
}
