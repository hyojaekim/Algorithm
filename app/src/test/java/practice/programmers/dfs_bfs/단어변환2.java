package practice.programmers.dfs_bfs;

import java.util.LinkedList;

public class 단어변환2 {

    int max;
    int answer;
    boolean[] select;
    String[] words;
    LinkedList<String> V = new LinkedList<>();

    public int solution(String begin, String target, String[] words) {
        if (!contains(words, target)) return 0;

        this.words = words;
        this.max = words.length;
        this.select = new boolean[max];
        this.answer = 0;

        for (int matchCount = 1; matchCount <= words.length; matchCount++) {
            dfs(0, matchCount, begin, target);
            if (answer != 0) return answer;
        }
        return answer;
    }

    private void dfs(int cnt, int matchCount, String begin, String target) {
        if (answer != 0) return;
        if (cnt == matchCount) {
            if (V.size() == 1) {
                if (isNext(begin, target)) answer = 1;
                return;
            }
            if (!V.get(V.size() - 1).equals(target)) return;
            String temp = begin;
            for (int i = 0; i < V.size(); i++) {
                if (!isNext(temp, V.get(i))) return;
                temp = V.get(i);
            }
            answer = matchCount;
            return;
        }

        for (int i = 0; i < max; i++) {
            if (select[i]) continue;
            select[i] = true;
            V.add(words[i]);
            dfs(cnt + 1, matchCount, begin, target);
            V.removeLast();
            select[i] = false;
        }
    }

    public boolean isNext(String begin, String word) {
        int count = 0;
        for (int i = 0; i < begin.length(); i++) {
            if (begin.charAt(i) != word.charAt(i)) count++;
            if (count > 1) return false;
        }
        return count == 1;
    }

    public boolean contains(String[] words, String target) {
        for (String word : words) {
            if (word.equals(target)) return true;
        }
        return false;
    }
}
