package programmers.dfs_bfs;

import java.util.LinkedList;

public class 단어변환 {

    int max;
    String[] words;
    boolean end = false;
    boolean[] select;
    LinkedList<String> V = new LinkedList<>();

    public int solution(String begin, String target, String[] words) {
        this.words = words;
        this.select = new boolean[words.length];
        this.max = words.length;
        int answer = 0;
        for (int i = 0; i < words.length; i++) {
            if (target.equals(words[i])) {
                end = false;
                break;
            } else {
                end = true;
            }
        }
        if (end) return 0;
        for (int i = 1; i <= words.length; i++) {
            dfs(i, 0, begin, target);
            if (end) return i;
        }
        return answer;
    }

    public void dfs(int selectCount, int count, String begin, String target) {
        if (end) return;
        if (count == selectCount) {
            String temp = begin;
            for (int i = 0; i < V.size(); i++) {
                if (!isContinue(temp, V.get(i))) return;
                temp = V.get(i);
            }
            if (target.equals(temp)) {
                end = true;
                return;
            }
        }
        for (int i = 0; i < max; i++) {
            if (select[i]) continue;
            select[i] = true;
            V.add(words[i]);
            dfs(selectCount, count + 1, begin, target);
            select[i] = false;
            V.removeLast();
        }
    }

    private boolean isContinue(String begin, String word) {
        int count = 0;
        for (int i = 0; i < begin.length(); i++) {
            if (begin.charAt(i) != word.charAt(i)) count++;
            if (count > 1) return false;
        }
        return count == 1;
    }
}
