package practice.programmers.kakao_2019_internship;

import java.util.HashMap;
import java.util.Map;

public class E_호텔방배정 {

    Map<Long, Long> rooms = new HashMap<>();

    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        for (int i = 0; i < room_number.length; i++) {
            Long roomNumber = findRoom(room_number[i]);
            rooms.put(roomNumber, findRoom(roomNumber + 1));
            answer[i] = roomNumber;
        }
        return answer;
    }

    public Long findRoom(Long roomNumber) {
        if (!rooms.containsKey(roomNumber)) {
            return roomNumber;
        }
        Long nextRoomNumber = findRoom(rooms.get(roomNumber));
        rooms.put(roomNumber, nextRoomNumber);
        return nextRoomNumber;
    }
}
