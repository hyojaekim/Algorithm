package practice.programmers.dfs_bfs;

public class 단어변환3 {

    int answer;
    String target;
    String[] reqWords;

    public int solution(String begin, String target, String[] reqWords) {
        this.answer = 0;
        this.target = target;
        this.reqWords = reqWords;
        //target이 없으면 종료
        boolean isPossible = false;
        for (String reqWord : reqWords) {
            if (target.equals(reqWord)) {
                isPossible = true;
                break;
            }
        }
        if (!isPossible) return answer;
        find(begin, begin, 0);
        return answer;
    }

    //target 찾기
    private void find(String words, String begin, int step) {
        if (begin.equals(target)) {
            answer = (answer != 0) ? Math.min(answer, step) : step;
            return;
        }
        if (words.split(",").length == reqWords.length) return;
        for (String reqWord : reqWords) {
            if (words.contains(reqWord)) continue;
            if (!isPossible(begin, reqWord)) continue;
            find(words + "," + reqWord, reqWord, step + 1);
        }
    }

    private boolean isPossible(String begin, String target) {
        int count = 0;
        for (int i = 0; i < begin.length(); i++) {
            if (begin.charAt(i) != target.charAt(i)) count++;
            if (count > 1) return false;
        }
        return count != 0;
    }
}
