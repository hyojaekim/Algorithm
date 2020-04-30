package programmers.kakao_2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 오픈채팅방 {

    private static final String LEAVE = "Leave";
    private static final String ENTER = "Enter";
    private static final String CHANGE = "Change";

    Map<String, String> userNicknames;
    List<Log> logs;

    public String[] solution(String[] record) {
        this.userNicknames = new HashMap<>();
        this.logs = new ArrayList<>();

        for (String message : record) {
            String[] splitMessage = message.split(" ");
            String state = splitMessage[0];
            String userId = splitMessage[1];
            String nickname = splitMessage.length == 2 ? userNicknames.get(userId) : splitMessage[2];
            if (state.equals(LEAVE)) this.logs.add(new Log(userId, nickname, false));
            else if (state.equals(ENTER)) this.logs.add(new Log(userId, userNicknames.put(userId, nickname), true));
            else if (state.equals(CHANGE)) userNicknames.put(userId, nickname);
        }

        String[] answer = new String[logs.size()];
        for (int i = 0; i < logs.size(); i++) {
            Log log = logs.get(i);
            log.changeNickname(userNicknames);
            answer[i] = log.toString();
        }
        return answer;
    }

    class Log {
        private String id;
        private String nickname;
        private boolean isEnter;

        public Log(String id, String nickname, boolean isEnter) {
            this.id = id;
            this.nickname = nickname;
            this.isEnter = isEnter;
        }

        public void changeNickname(Map<String, String> nickname) {
            this.nickname = nickname.get(id);
        }

        @Override
        public String toString() {
            return new StringBuilder().append(nickname)
                    .append("님이")
                    .append(isEnter ? " 들어왔습니다." : " 나갔습니다.")
                    .toString();
        }
    }
}
