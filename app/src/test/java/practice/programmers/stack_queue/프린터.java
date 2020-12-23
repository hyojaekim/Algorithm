package practice.programmers.stack_queue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 프린터 {

    public int solution2(int[] priorities, int location) {
        int answer = 0;
        //순서대로 넣기
        Queue<Paper> q = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            q.add(new Paper(priorities[i], i));
        }

        //큐가 비어 있을때 까지
        //하나를 꺼낸다.
        //true : 꺼낸 애가 제일 크면 location에 위치한 애가 맞는지 확인
        //false : 뒤에 있는 애들이 얘보다 큰게 있으면 다시 넣음
        List<Paper> waitPapers;
        while (!q.isEmpty()) {
            Paper paper = q.poll();
            waitPapers = (List<Paper>) q;
            boolean isBack = false;
            for (int i = 0; i < waitPapers.size(); i++) {
                if (waitPapers.get(i).priority > paper.priority) {
                    isBack = true;
                    break;
                }
            }
            if (isBack) {
                q.add(paper);
            } else {
                answer++;
                if (location == paper.location) {
                    return answer;
                }
            }
        }
        return answer;
    }
}

class Paper {
    int priority;
    int location;

    public Paper(int priority, int location) {
        this.priority = priority;
        this.location = location;
    }
}
