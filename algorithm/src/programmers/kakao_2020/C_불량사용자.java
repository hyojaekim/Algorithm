package programmers.kakao_2020;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class C_불량사용자 {

    String[] userId;
    String[] banId;
    boolean[] select;
    LinkedList<String> V = new LinkedList<>();
    Set<Set<String>> result = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        this.userId = user_id;
        this.banId = banned_id;
        this.select = new boolean[user_id.length];
        dfs(0, banId.length);
        return result.size();
    }

    private void dfs(int cnt, int selectCount) {
        if (cnt == selectCount) {
            for (int i = 0; i < V.size(); i++) {
                if (!isMatch(banId[i], V.get(i))) return;
            }
            result.add(new HashSet<>(V));
        }
        for (int i = 0; i < userId.length; i++) {
            if (select[i]) continue;
            select[i] = true;
            V.add(userId[i]);
            dfs(cnt + 1, selectCount);
            V.removeLast();
            select[i] = false;
        }
    }

    private boolean isMatch(String banId, String userId) {
        if (banId.length() != userId.length()) return false;
        for (int i = 0; i < banId.length(); i++) {
            if (banId.charAt(i) == '*') continue;
            if (banId.charAt(i) != userId.charAt(i)) return false;
        }
        return true;
    }
}
